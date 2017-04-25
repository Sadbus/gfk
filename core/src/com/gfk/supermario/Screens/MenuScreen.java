package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gfk.supermario.GameRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.ApplicationListener;

public class MenuScreen extends ApplicationAdapter implements Screen, ApplicationListener

{
    final GameRenderer game;
    OrthographicCamera camera;
    Texture background;
    Texture playbutton;

    public MenuScreen(final GameRenderer game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void create() {

        background = new Texture(Gdx.files.internal("menuscreen.png")); // jobber med å laste inn menuscreen.png som bakgrunnbildet
        playbutton = new Texture(Gdx.files.internal("playbutton.png"));

    }

    @Override
    public void render(float delta)
    {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Click the play button to start the game ", 200, 100);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))

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
        background.dispose();
        playbutton.dispose();

    }
}