package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
    public Enemy (GameScreen screen, float x, float y)
    {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
    public abstract void hitOnHead();
}
