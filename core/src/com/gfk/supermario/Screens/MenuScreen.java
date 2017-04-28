package com.gfk.supermario.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gfk.supermario.GameRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
    private GameRenderer game;
    private OrthographicCamera camera;
    protected Stage stage;
    private FitViewport viewport;
    private Table table;


    private Texture startTexture;
    private Texture aboutTexture;
    private Texture optionsTexture;

    private TextureRegion startTextureRegion;
    private TextureRegion aboutTextureRegion;
    private TextureRegion optionsTextureRegion;

    private TextureRegionDrawable startTexRegionDrawable;
    private TextureRegionDrawable aboutTexRegionDrawable;
    private TextureRegionDrawable optionsTexRegionDrawable;

    private ImageButton startButton;
    private ImageButton aboutButton;
    private ImageButton optionsButton;

    private Texture welcome;
    private Texture keymaster;
    private Texture background;

    private BitmapFont font;

    Music music;

    public MenuScreen(final GameRenderer game) {
        this.game = game;

        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);
        //camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

        viewport = new FitViewport(800, 400, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        startTexture = new Texture(Gdx.files.internal("start.png"));
        aboutTexture = new Texture(Gdx.files.internal("about.png"));
        optionsTexture = new Texture(Gdx.files.internal("options.png"));

        startTextureRegion = new TextureRegion(startTexture);
        startTexRegionDrawable = new TextureRegionDrawable(startTextureRegion);
        aboutTextureRegion = new TextureRegion(aboutTexture);
        aboutTexRegionDrawable = new TextureRegionDrawable(aboutTextureRegion);
        optionsTextureRegion = new TextureRegion(optionsTexture);
        optionsTexRegionDrawable = new TextureRegionDrawable(optionsTextureRegion);


        music = Gdx.audio.newMusic(Gdx.files.internal("intro.mp3"));
        font = new BitmapFont();


        stage = new Stage(viewport, game.batch);

        Gdx.input.setInputProcessor(stage);

        welcome = new Texture("welcome.png");
        keymaster = new Texture("keymaster.png");
        background = new Texture("menuscreen1.png");
    }

    @Override
    public void show() {
        // Create table, fill stage and align top.
        table = new Table();
        table.setFillParent(true);
        table.center();


        // Create buttons
        startButton = new ImageButton(startTexRegionDrawable);
        //table.row();
        aboutButton = new ImageButton(aboutTexRegionDrawable);
        //table.row();
        optionsButton = new ImageButton(optionsTexRegionDrawable);

        music.play();

        // Create ClickListeners for buttons
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                System.out.println("Start New game");
            }
        });
        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked about");
            }
        });
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked Options");
            }
        });


        // Add buttons to table
        table.add(aboutButton);
        table.add(startButton);
        table.add(optionsButton);

        //TODO Add padding between buttons
        //table.pad(50);

        // Add buttons table stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta)
    {
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(welcome, 200, 280);
        game.batch.draw(keymaster, 120, 240);
        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        background.dispose();
        welcome.dispose();
        keymaster.dispose();
        music.dispose();
    }
}