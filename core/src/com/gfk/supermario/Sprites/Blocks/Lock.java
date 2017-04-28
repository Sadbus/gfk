package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.gfk.supermario.Entities.Hero;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

import javax.swing.JOptionPane;

/**
 * Created by Olav on 27.04.2017.
 */
public class Lock extends TileObject {
    public Lock(GameScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.LOCK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Lock", "Collision");
        
        if (Hero.hasKey){
            setCategoryFilter(GameRenderer.DESTROYED_BIT);
            // Temporary, this should initiate a new screen with option to exit or go to next map.
            JOptionPane.showMessageDialog(null, "You won");
        }
    }
}
