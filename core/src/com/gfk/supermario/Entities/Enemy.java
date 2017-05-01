package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Nat on 30/04/2017.
 */
public abstract class Enemy extends Sprite
{
    protected World world;
    protected GameScreen screen;
    public Body b2body;

    public Vector2 floating;
    public Enemy (GameScreen screen, float x, float y)
    {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        floating = new Vector2(1, 0);
    }

    protected abstract void defineEnemy();
    public abstract void hitOnHead();

    public void reverseFloating (boolean x, boolean y)
    {
        if(x)
            floating.x = - floating.x;
        if(y)
            floating.y = - floating.y;
    }
}
