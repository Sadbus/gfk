package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gfk.supermario.Entities.Hero;
import com.gfk.supermario.GameRenderer;

import javax.swing.*;


/**
 * Created by Olav on 27.04.2017.
 */
public class Lock extends TileObject {
    public Lock(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.LOCK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Lock", "Collision");
        setCategoryFilter(GameRenderer.DESTROYED_BIT);


        if (Hero.hasKey){
            // Temporary, this should initiate a new screen with option to exit or go to next map.
            JOptionPane.showMessageDialog(null, "You won");
        }
    }
}
