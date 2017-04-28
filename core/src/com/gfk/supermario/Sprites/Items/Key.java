package com.gfk.supermario.Sprites.Items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gfk.supermario.Entities.Hero;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Scenes.HUD;

/**
 * Created by Olav on 27.04.2017.
 */
public class Key extends ItemObject {
    public Key(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.KEY_BIT);
    }

    @Override
    public void onHit() {
        setCategoryFilter(GameRenderer.DESTROYED_BIT);
        getCell().setTile(null);
        Hero.hasKey = true;
        HUD.gotKey();
        HUD.addScore(300);
    }
}
