package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gfk.supermario.GameRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends ApplicationAdapter implements Screen

{
    final GameRenderer game;
    OrthographicCamera camera;

    SpriteBatch batch;
    Texture img;

    public MenuScreen(final GameRenderer game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        img = new Texture("menuscreen.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
/*
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //game.font.draw(game.batch, "Click the play button to start the game ", 200, 100);
        batch.draw(img, 0, 0);
        batch.end();

  */

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //game.font.draw(game.batch, "Click the play button to start the game ", 200, 100);

        batch.draw(img, 250, 0);
        batch.end();

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

        batch.dispose();
        img.dispose();

    }
}