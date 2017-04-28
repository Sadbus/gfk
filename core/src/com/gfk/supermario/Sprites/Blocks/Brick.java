package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Olav on 26.04.2017.
 */
public class Brick extends TileObject
{
    public Brick(GameScreen screen, MapObject object)
    {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.BRICK_BIT);
    }

    @Override
    public void onHeadHit()
    {
        Gdx.app.log("Brick", "Collision");
        //setCategoryFilter(GameRenderer.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
