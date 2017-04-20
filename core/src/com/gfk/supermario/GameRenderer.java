package com.gfk.supermario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gfk.supermario.Screens.MenuScreen;

public class GameRenderer extends Game
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Games For Kekistan";


    public SpriteBatch batch;
    public BitmapFont font;


    @Override
	public void create ()
	{
		batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
        font = new BitmapFont();
	}

	@Override
    public void render()
    {
        super.render();
    }
}
