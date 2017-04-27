package com.gfk.supermario.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gfk.supermario.GameRenderer;

import javax.swing.*;

/**
 * Created by Nat on 25/04/2017.
 */
public class HUD
{
    public Stage stage;
    private Viewport viewport;

    private Integer score;

    Label scoreLabel;
    Label pointLabel;
    Label levelLabel;
    Label worldLabel;
    Label keyLabel;

    public HUD(SpriteBatch sb)
    {
        score = 0;

        viewport = new FitViewport(GameRenderer.WIDTH, GameRenderer.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pointLabel = new Label("Keys", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("First Level", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        keyLabel = new Label("Key Master", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(keyLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(pointLabel).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }
}
