package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Scenes.HUD;

/**
 * Created by Olav on 26.04.2017.
 */
public class Coin extends TileObject {
    public Coin(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        // This will be changed to breaking the block and updating score
        Gdx.app.log("Coin", "Collision");
        setCategoryFilter(GameRenderer.DESTROYED_BIT);
        getCell().setTile(null);
        HUD.incrementScore();
    }
}
