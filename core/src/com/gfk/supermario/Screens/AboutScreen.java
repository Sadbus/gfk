package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Adrian on 28.04.2017.
 */
public class AboutScreen implements Screen {
    private GameRenderer game;
    protected Stage stage;
    private TextureAtlas atlas;
    private Skin skin;

    Image bgImage;
    Image title;

    private TextButton backButton;
    private Table table;

    public AboutScreen(final GameRenderer game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("UI.pack");
        skin = new Skin();
        skin.addRegions(atlas);

        // Create background image
        bgImage = new Image(new Texture("sky1.png"));

        // Define button
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button05");
        skin.add("default", textButtonStyle);

        title = new Image(new Texture("keymaster.png"));


        // skin refers to default textButtonStyle
        backButton = new TextButton("Go back", skin);
        backButton.setPosition(Gdx.graphics.getWidth() /2 - backButton.getWidth() / 2, Gdx.graphics.getHeight() / 10);

        table = new Table();

        Label gruppe = new Label("Gruppe:\nOlav Markus Sjurs\nMorten Mikalsen \nAdrian Adamski ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label text = new Label("Vi har laget en platformer ved hjelp av biblioteket LibGDX, for fysikk har vi brukt en port av Box2D som følger med LibGX. Spillkartet har blitt laget ved hjelp av et program som heter Tiled.", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        text.setWrap(true);
        text.setWidth(100);
        table.setPosition(Gdx.graphics.getWidth() / 2- table.getWidth() / 2, Gdx.graphics.getHeight() / 2f - table.getHeight() / 2);
        table.add(gruppe).padBottom(30);
        table.row();
        table.add(text).width(600f);
    }

    @Override
    public void show() {
        title.setPosition(Gdx.graphics.getWidth()/2-title.getWidth()/2,Gdx.graphics.getHeight()/2+title.getHeight()/3);


        stage.addActor(bgImage);
        stage.addActor(title);
        stage.addActor(backButton);
        stage.addActor(table);


        backButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                game.setScreen(new MenuScreen(game));
            }
        });
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
        skin.dispose();
        atlas.dispose();
        game.dispose();

    }
}
