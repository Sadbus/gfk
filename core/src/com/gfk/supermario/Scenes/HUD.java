package com.gfk.supermario.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Nat on 25/04/2017.
 */
public class HUD
{
    public Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Viewport viewport;

    public static Integer score;

    static Label scoreLabel;
    Label pointLabel;
    Label levelLabel;
    Label worldLabel;
    static Label keyLabel;

    static ImageButton key;

    public HUD(SpriteBatch sb)
    {
        viewport = new FitViewport(GameRenderer.WIDTH, GameRenderer.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        atlas = new TextureAtlas("TexturePack.pack");
        skin = new Skin();
        skin.addRegions(atlas);

        score = 0;

        buttonStyle();

        key = new ImageButton(skin);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pointLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("First Level", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("KeyMaster", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        keyLabel = new Label("Find the key!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(key).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(pointLabel).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    public static void gotKey()
    {
        key.setChecked(true);
    }
    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%04d", score));
    }

    public void buttonStyle() {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("10");
        imageButtonStyle.checked = skin.getDrawable("9");

        skin.add("default", imageButtonStyle);
    }
}
