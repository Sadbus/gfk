package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Olav on 01.05.2017.
 */
public class Death extends TileObject{
    public Death(GameScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.DEATH_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Death", "Collision");
    }
}
