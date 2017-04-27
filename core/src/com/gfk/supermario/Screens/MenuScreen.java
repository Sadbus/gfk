package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gfk.supermario.GameRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Music;


public class MenuScreen extends ApplicationAdapter implements Screen, ApplicationListener

{
    final GameRenderer game;
    OrthographicCamera camera;
    private Texture welcome;
    private Texture keymaster;
    private Texture menuscreen;
    private Texture start;
    private Texture about;
    private Texture options;
    private SpriteBatch batch;
    private BitmapFont font;
    Music music;



    public MenuScreen(final GameRenderer game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        welcome = new Texture("welcome.png");
        keymaster = new Texture("keymaster.png");
        menuscreen = new Texture("menuscreen1.png");
        start = new Texture("start.png");
        about = new Texture("about.png");
        options = new Texture("options.png");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        music= Gdx.audio.newMusic(Gdx.files.internal("intro.mp3"));

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
        batch.draw(start, 100, 230);
        batch.draw(about, 320, 230);
        batch.draw(options, 550, 230);
        batch.draw(welcome, 200, 350);
        batch.draw(keymaster, 120, 280);
        //font.draw(batch, "WELCOME TO ", 350, 420);
        //font.draw(batch, "KEY MASTER; THE QUEST FOR KEYS", 270, 390);
        batch.end();

        if(Gdx.input.justTouched())


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
        start.dispose();
        options.dispose();
        about.dispose();
        welcome.dispose();
        keymaster.dispose();
        font.dispose();
        music.dispose();
    }
}