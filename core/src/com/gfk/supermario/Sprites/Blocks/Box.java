package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Scenes.HUD;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Olav on 26.04.2017.
 */
public class Box extends TileObject {
    public Box(GameScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(GameRenderer.BOX_BIT);
    }

    @Override
    public void onHeadHit() {
        setCategoryFilter(GameRenderer.DESTROYED_BIT);
        getCell().setTile(null);
        HUD.addScore(25);
        GameRenderer.manager.get("audio/sounds/break_box.ogg", Sound.class).play(GameRenderer.prefs.getFloat("soundVolume"));



    }
}
