package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
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

    //private Hero hero;

    public GameScreen(GameRenderer game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        cameraPort = new FitViewport(800, 480, camera);

        shootingStars = Gdx.audio.newMusic(Gdx.files.internal("mario.mp3"));
        shootingStars.setLooping(true);

        //Load map
        tiledMap = new TmxMapLoader().load("testmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera.position.set(cameraPort.getScreenWidth() / 2, cameraPort.getScreenHeight() / 2, 0);

        //hero = new Hero(this);
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


        game.batch.setProjectionMatrix(camera.combined);


        camera.update();
        tiledMapRenderer.setView(camera);
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
            //hero.jump();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            //hero.moveForward();
            camera.position.x += 100 * dt;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            //hero.moveBackward();

        }
    }

    public void update(float dt)
    {
        handleInput(dt);
        //camera.update();
        //tiledMapRenderer.setView(camera);
        //hero.update(dt);
    }

    @Override
    public void dispose()
    {
        shootingStars.dispose();
        tiledMap.dispose();

    }
}