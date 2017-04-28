package com.gfk.supermario.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gfk.supermario.GameRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends ApplicationAdapter implements Screen, ApplicationListener

{
    final GameRenderer game;
    OrthographicCamera camera;
    private Texture welcome;
    private Texture keymaster;
    private Texture menuscreen;
    //private Texture start;
    //private Texture about;
    //private Texture options;
    private SpriteBatch batch;
    private BitmapFont font;
    Music music;

    private Stage stage1;
    private Stage stage2;
    private Stage stage3;

    private Texture myTexture1;
    private Texture myTexture2;
    private Texture myTexture3;

    private TextureRegion myTextureRegion1;
    private TextureRegion myTextureRegion2;
    private TextureRegion myTextureRegion3;

    private TextureRegionDrawable myTexRegionDrawable1;
    private TextureRegionDrawable myTexRegionDrawable2;
    private TextureRegionDrawable myTexRegionDrawable3;

    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;



    public MenuScreen(final GameRenderer game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        welcome = new Texture("welcome.png");
        keymaster = new Texture("keymaster.png");
        menuscreen = new Texture("menuscreen1.png");
        //start = new Texture("start.png");
        //about = new Texture("about.png");
        //options = new Texture("options.png");
        font = new BitmapFont();
        music = Gdx.audio.newMusic(Gdx.files.internal("intro.mp3"));

        myTexture1 = new Texture(Gdx.files.internal("start.png"));
        myTexture2 = new Texture(Gdx.files.internal("about.png"));
        myTexture3 = new Texture(Gdx.files.internal("options.png"));

        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        myTextureRegion3 = new TextureRegion(myTexture3);
        myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);

        button1 = new ImageButton(myTexRegionDrawable1);
        button2 = new ImageButton(myTexRegionDrawable2);
        button3 = new ImageButton(myTexRegionDrawable3);

        stage1 = new Stage(new ScreenViewport());
        stage1.addActor(button1);
        stage2 = new Stage(new ScreenViewport());
        stage2.addActor(button2);
        stage3 = new Stage(new ScreenViewport());
        stage3.addActor(button3);

        //Gdx.input.setInputProcessor(stage1);
        //Gdx.input.setInputProcessor(stage2);
        //Gdx.input.setInputProcessor(stage3);

    }

    @Override
    public void show() {

        music.play();

    }

    @Override
    public void render(float delta)
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(menuscreen, 0, 0);
        //batch.draw(start, 320, 230);
        //batch.draw(about, 100, 230);
        //batch.draw(options, 550, 230);
        batch.draw(welcome, 200, 350);
        batch.draw(keymaster, 120, 280);
        batch.end();

        stage1.draw();
        stage2.draw();
        stage3.draw();
        button1.setPosition(450, 290);
        button2.setPosition(250, 290);
        button3.setPosition(640, 290);

                //if(Gdx.input.justTouched())
                if(button1.isPressed()) // Hvordan få start knappen til å funke? Kanskje ved hjelp av ClickListener?

                {
                    game.setScreen(new GameScreen(game));
                    music.stop();
                }

    }

    @Override
    public void resize(int width, int height) {

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

        batch.dispose();
        menuscreen.dispose();
        //start.dispose();
        //options.dispose();
        //about.dispose();
        welcome.dispose();
        keymaster.dispose();
        font.dispose();
        music.dispose();
    }
}