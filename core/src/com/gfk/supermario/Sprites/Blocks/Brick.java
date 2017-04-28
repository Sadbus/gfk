package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.maps.MapObject;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Scenes.HUD;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Olav on 26.04.2017.
 */
public class Brick extends TileObject {
    public Brick(GameScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        setCategoryFilter(GameRenderer.DEFAULT_BIT);
        getCell().setTile(null);
        HUD.addScore(25);
    }
}
