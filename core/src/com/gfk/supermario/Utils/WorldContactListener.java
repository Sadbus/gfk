package com.gfk.supermario.Utils;

import com.badlogic.gdx.physics.box2d.*;
import com.gfk.supermario.Sprites.Blocks.TileObject;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Sprites.Items.ItemObject;

/**
 * Created by Olav on 26.04.2017.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case GameRenderer.HERO_HEAD_BIT | GameRenderer.LOCK_BIT:
            case GameRenderer.HERO_HEAD_BIT | GameRenderer.BOX_BIT:
            case GameRenderer.HERO_HEAD_BIT | GameRenderer.COIN_BIT:
                if(fixA.getFilterData().categoryBits == GameRenderer.HERO_HEAD_BIT)
                    ((TileObject) fixB.getUserData()).onHeadHit();
                else
                    ((TileObject) fixA.getUserData()).onHeadHit();
                break;
            case GameRenderer.HERO_BIT | GameRenderer.KEY_BIT:
                if(fixA.getFilterData().categoryBits == GameRenderer.HERO_BIT)
                    ((ItemObject) fixB.getUserData()).onHit();
                else
                    ((ItemObject) fixA.getUserData()).onHit();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
