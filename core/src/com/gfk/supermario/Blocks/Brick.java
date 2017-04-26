package com.gfk.supermario.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Olav on 26.04.2017.
 */
public class Brick extends InteractiveTIleObject {
    public Brick(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(GameRenderer.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
