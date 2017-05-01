package com.gfk.supermario.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gfk.supermario.GameRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen implements Screen {
    private GameRenderer game;
    protected Stage stage;
    private TextureAtlas atlas;
    private Skin skin;

    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton aboutButton;
    private TextButton exitButton;

    private Music music;

    private Table table;

    private Image background;
    private Image subTitle;
    private Image title;

    public MenuScreen(final GameRenderer game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("UI/UI.pack");
        skin = new Skin();
        skin.addRegions(atlas);

        table = new Table();

        music = Gdx.audio.newMusic(Gdx.files.internal("audio/music/menu_music.mp3"));

        buttonStyle();

        background = new Image(new Texture("UI/menu_bg.png"));
        subTitle = new Image(new Texture("UI/welcome.png"));
        title = new Image(new Texture("UI/keymaster.png"));

        startButton = new TextButton("New Game", skin);
        optionsButton = new TextButton("Options", skin);
        aboutButton = new TextButton("About", skin);
        exitButton = new TextButton("Exit", skin);
    }

    @Override
    public void show() {
        // Create ClickListeners for buttons
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                music.stop();
                game.setScreen(new GameScreen(game));
            }
        });
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                music.stop();
                game.setScreen(new OptionsScreen(game));
            }
        });
        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                music.stop();
                game.setScreen(new AboutScreen(game));
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Set position for images
        subTitle.setPosition(Gdx.graphics.getWidth() / 2 - subTitle.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + subTitle.getHeight() / 1.2f);
        title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth()/ 2,
                Gdx.graphics.getHeight() / 2 + title.getHeight() / 3);
        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);


        // Create table, fill stage and align center.
        table.setFillParent(true);
        table.center();
        table.padTop(150);

        // Add buttons to table
        table.add(startButton);
        table.row();
        table.add(optionsButton).pad(20);
        table.row();
        table.add(aboutButton);
        table.row().pad(20);
        table.add(exitButton);

        stage.addActor(background);
        stage.addActor(subTitle);
        stage.addActor(title);
        stage.addActor(table);

        music.play();
        music.setVolume(game.musicVolume);
    }

    public void buttonStyle() {
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button05");
        skin.add("default", textButtonStyle);
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
}