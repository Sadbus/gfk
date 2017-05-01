package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Olav on 01.05.2017.
 */
public class winScreen implements Screen {
    private GameRenderer game;
    protected Stage stage;
    private TextureAtlas atlas;
    private Skin skin;

    private TextButton menuButton;
    private TextButton exitButton;

    private Music music;

    private Table table;

    private Image background;
    private Image win;

    public winScreen(final GameRenderer game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("UI/UI.pack");
        skin = new Skin();
        skin.addRegions(atlas);

        buttonStyle();

        table = new Table();

        music = Gdx.audio.newMusic(Gdx.files.internal("audio/music/menu_music.mp3"));

        background = new Image(new Texture("UI/menu_bg.png"));
        win = new Image(new Texture("UI/win.png"));

        menuButton = new TextButton("Exit to Main Menu", skin);
        exitButton = new TextButton("Exit to Desktop", skin);
    }

    @Override
    public void show() {
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

        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);
        win.setPosition(Gdx.graphics.getWidth() / 2 - win.getWidth()/ 2,
                Gdx.graphics.getHeight() / 2 + win.getHeight() / 3);

        table.setFillParent(true);
        table.center();
        table.padTop(150);

        table.add(menuButton);
        table.row().pad(20);
        table.add(exitButton);

        stage.addActor(background);
        stage.addActor(table);
        stage.addActor(win);

        music.play();
        music.setVolume(game.musicVolume);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        music.dispose();
        skin.dispose();
        atlas.dispose();
        game.dispose();
    }

    public void buttonStyle() {
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button05");
        skin.add("default", textButtonStyle);
    }
}
