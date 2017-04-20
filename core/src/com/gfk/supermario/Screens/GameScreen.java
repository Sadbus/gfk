package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Olav Markus on 19.04.2017.
 */
public class GameScreen implements Screen{
    private GameRenderer game;
    Texture texture;

    Music shootingStars;

    public GameScreen(GameRenderer game)
    {
        this.game = game;
        texture = new Texture("badlogic.jpg");
        shootingStars = Gdx.audio.newMusic(Gdx.files.internal("mario.mp3"));
        shootingStars.setLooping(true);
    }

    @Override
    public void show() {
        shootingStars.play();

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(texture, 0, 0);
        game.batch.end();
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
        shootingStars.dispose();

    }
}