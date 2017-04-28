package com.gfk.supermario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gfk.supermario.Screens.MenuScreen;

public class GameRenderer extends Game
{
    //Tile bredde * antall tiles i høyden
    public static final int HEIGHT = 273;
    //Bredde skal være slik at sideforholdet blir 16:9
    public static final int WIDTH = 485;

    //"Pixels per Meter", brukes for skalering.
    public static final float PPM = 100;

    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short HERO_HEAD_BIT = 4;
    public static final short BRICK_BIT = 8;
    public static final short COIN_BIT = 16;
    public static final short DESTROYED_BIT = 32;
    public static final short KEY_BIT = 64;
    public static final short LOCK_BIT = 128;
    public static final short MOVABLE_TILE_BIT = 256;

    public static final String TITLE = "Key Master; The quest for keys";


    public SpriteBatch batch;
    public BitmapFont font;



    @Override
    public void create ()
    {
        batch = new SpriteBatch();
        font = new BitmapFont();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        batch.dispose();
    }
}