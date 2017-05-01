package com.gfk.supermario.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gfk.supermario.Entities.Hero;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Sprites.Blocks.MovableBox;
import com.gfk.supermario.Utils.WorldContactListener;
import com.gfk.supermario.Utils.initWorld;
import com.gfk.supermario.Scenes.HUD;

import static com.gfk.supermario.Screens.GameScreen.State.PAUSE;
import static com.gfk.supermario.Screens.GameScreen.State.RUNNING;

/**
 * Created by Olav Markus on 19.04.2017.
 */
public class GameScreen implements Screen {
    private GameRenderer game;
    private TextureAtlas atlas;
    private TextureAtlas atlas2;

    private OrthographicCamera camera;
    private FitViewport cameraPort;

    //Tiled map
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    //  Box2D - Physics
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private HUD hud;
    private Hero hero;
    private MovableBox box;
    private MovableBox box2;
    private MovableBox box3;

    private Music music;

    public enum State{PAUSE, RUNNING, RESUME}
    State state;

    //Pause
    protected Stage stage;
    private Skin skin;

    private TextButton resumeButton;
    private TextButton menuButton;
    private TextButton exitButton;

    private Table table;

    private Image background;
    private Image title;

    public static boolean hasWon;

    public GameScreen(GameRenderer game)
    {
        atlas = new TextureAtlas("TexturePack.pack");

        this.game = game;
        hud = new HUD(game.batch);

        state = RUNNING;

        camera = new OrthographicCamera();
        cameraPort = new FitViewport(game.WIDTH / game.PPM, game.HEIGHT / game.PPM, camera);

        //Load map
        if (game.prefs.getInteger("level") == 1) {
            tiledMap = new TmxMapLoader().load("Level1.tmx");
            System.out.println("Displaying map 1");
        } else {
            tiledMap = new TmxMapLoader().load("Level2.tmx");
            System.out.println("Displaying map 2");

        }

            tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / game.PPM);

        //Sentrerer kamera.
        camera.position.set(cameraPort.getWorldWidth() / 2, cameraPort.getWorldHeight() / 2, 0);

        //  Box2D - Physics
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        new initWorld(this);
        world.setContactListener(new WorldContactListener());

        hero = new Hero(world, this);
        box = new MovableBox(this, 1290, 200);
        box2 = new MovableBox(this, 1570, 210);
        box3 = new MovableBox(this, 1690, 40);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas2 = new TextureAtlas("UI/UI.pack");
        skin = new Skin();
        skin.addRegions(atlas2);

        buttonStyle();

        table = new Table();
        background = new Image(new Texture("UI/menu_bg.png"));
        title = new Image(new Texture("UI/keymaster.png"));

        resumeButton = new TextButton("Resume", skin);
        menuButton = new TextButton("Exit to Main Menu", skin);
        exitButton = new TextButton("Exit to Desktop", skin);

        music = GameRenderer.manager.get("audio/music/game_music.mp3", Music.class);
        music.setVolume(game.prefs.getFloat("musicVolume"));
        music.setLooping(true);
        music.play();
    }

    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    @Override
    public void show() {

        // Create ClickListeners for buttons
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                resume();
            }
        });
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                music.stop();
                game.setScreen(new MenuScreen(game));
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Set position for images
        title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth()/ 2,
                Gdx.graphics.getHeight() / 2 + title.getHeight() / 3);
        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);


        // Create table, fill stage and align center.
        table.setFillParent(true);
        table.center();
        table.padTop(150);

        // Add buttons to table
        table.add(resumeButton);
        table.row();
        table.add(menuButton).pad(20);
        table.row();
        table.add(exitButton);

        stage.addActor(background);
        stage.addActor(title);
        stage.addActor(table);

    }

    @Override
    public void render(float delta)
    {
        switch (state) {
            case RUNNING:
                renderRunning(delta);
                break;
            case PAUSE:
                renderPause(delta);
                break;
            case RESUME:

                break;
        }
    }

    public void renderRunning(float dt){
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.render();

        box2DDebugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        hero.draw(game.batch);
        box.draw(game.batch);
        box2.draw(game.batch);
        box3.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void renderPause(float dt){
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        cameraPort.update(width, height);
    }

    @Override
    public void pause() {
        music.pause();
        this.state = PAUSE;

    }

    @Override
    public void resume() {
        this.state = RUNNING;
        music.play();
    }

    @Override
    public void hide() {

    }

    public void handleInput(float dt)
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            //hero.b2body.applyLinearImpulse(new Vector2(0, 4f),hero.b2body.getWorldCenter(), true);
            hero.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && hero.b2body.getLinearVelocity().x <= 2)
        {
            hero.b2body.applyLinearImpulse(new Vector2(0.1f, 0),
                    hero.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && hero.b2body.getLinearVelocity().x >= -2)
        {
            hero.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),
                    hero.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            pause();
        }
    }

    public void update(float dt)
    {
        switch (state)
        {
            case RUNNING:
                updateRunning(dt);
                break;
            case PAUSE:
                updatePaused(dt);
                break;
            case RESUME:
                renderRunning(dt);
                break;
        }

        if (hasWon)
        {
            hasWon = false;
            int test = game.prefs.getInteger("level");
            game.prefs.putInteger("level", game.prefs.getInteger("level") + 1);
            music.stop();
            if (game.prefs.getInteger("level") == 2){
                game.setScreen(new nextLevelScreen(game));
            }
            else
            {
                System.out.println("You win!");
                game.setScreen(new winScreen(game));
            }

        }
    }

    public void updateRunning(float dt){
        handleInput(dt);
        world.step(1/60f, 6, 2);
        camera.position.x = hero.b2body.getPosition().x;
        camera.update();
        tiledMapRenderer.setView(camera);
        hero.update(dt);
        box.update(dt);
        box2.update(dt);
        box3.update(dt);
    }

    public void updatePaused(float dt){
        handleInput(dt);
    }

    @Override
    public void dispose()
    {
        music.dispose();
        tiledMap.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
        atlas.dispose();
        stage.dispose();
        skin.dispose();
    }

    public void buttonStyle() {
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button05");
        skin.add("default", textButtonStyle);
    }


    public TiledMap getTiledMap(){
        return tiledMap;
    }
    public World getWorld(){
        return world;
    }
}