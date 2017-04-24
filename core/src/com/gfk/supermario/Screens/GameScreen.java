package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gfk.supermario.Entities.Hero;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Olav Markus on 19.04.2017.
 */
public class GameScreen implements Screen{
    private GameRenderer game;

    private OrthographicCamera camera;
    private FitViewport cameraPort;

    //Tiled map
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    Music shootingStars;

    //  Box2D - Physics
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Body body;

    private Hero hero;

    public GameScreen(GameRenderer game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        cameraPort = new FitViewport(GameRenderer.WIDTH / GameRenderer.PPM, GameRenderer.HEIGHT / GameRenderer.PPM, camera);

        //  Box2D - Physics
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //Load map
        tiledMap = new TmxMapLoader().load("Kenney.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / GameRenderer.PPM);

        //Sentrerer kamera.
        camera.position.set(cameraPort.getWorldWidth() / 2, cameraPort.getWorldHeight() / 2, 0);

        //Innlasting av objekter skal flyttes over til "InitWorld"

        //Create ground
        for(MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        /*
        //create pipe
        for (MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        */

        //create brick
        for (MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //create coin
        for (MapObject object : tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }


        shootingStars = Gdx.audio.newMusic(Gdx.files.internal("mario.mp3"));
        shootingStars.setLooping(true);


        hero = new Hero(world);
    }



    @Override
    public void show()
    {
        shootingStars.play();

    }

    @Override
    public void render(float delta)
    {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.render();

        box2DDebugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);

        //game.batch.begin();
        hero.draw(game.batch);
        //game.batch.draw(hero.sprite, hero.b2body.getPosition().x, hero.b2body.getPosition().y);
        //game.batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        cameraPort.update(width, height);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    public void handleInput(float dt)
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            hero.b2body.applyLinearImpulse(new Vector2(0, 4f), hero.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && hero.b2body.getLinearVelocity().x <= 2){
            hero.b2body.applyLinearImpulse(new Vector2(0.1f, 0), hero.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && hero.b2body.getLinearVelocity().x <= 2){
            hero.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), hero.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt)
    {
        handleInput(dt);

        world.step(1/60f, 6, 2);

        camera.position.x = hero.b2body.getPosition().x;




        camera.update();
        tiledMapRenderer.setView(camera);
        hero.update(dt);
    }

    @Override
    public void dispose()
    {
        shootingStars.dispose();
        tiledMap.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
    }
}