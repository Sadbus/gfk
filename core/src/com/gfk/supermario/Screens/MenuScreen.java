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


public class MenuScreen extends ApplicationAdapter implements Screen, ApplicationListener

{
    final GameRenderer game;
    OrthographicCamera camera;
    TextButton button;
    TextButton.TextButtonStyle buttonStyle;


    private SpriteBatch batch3;
    private Texture menuscreen;

    private SpriteBatch batch2;
    private Texture playbutton;

    private SpriteBatch batch;
    private BitmapFont font;


    public MenuScreen(final GameRenderer game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        menuscreen = new Texture("menuscreen.png");
        batch2 = new SpriteBatch();
        playbutton = new Texture("playbutton.png");
        batch3 = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(menuscreen, 250, 0);
        batch.draw(playbutton, 350, 240);
        font.draw(batch, "Click the play button to start the game", 275, 360);
        batch.end();

        if(Gdx.input.justTouched())


        {
            game.setScreen(new GameScreen(game));
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
        batch2.dispose();
        playbutton.dispose();
        batch3.dispose();
        font.dispose();
    }
}