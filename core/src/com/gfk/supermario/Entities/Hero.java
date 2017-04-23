package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by olav on 20.04.17.
 */
public class Hero extends Sprite{
    public World world;
    public Body b2body;

    private GameScreen screen;

    public Hero(World world)
    {
    this.world = world;
    defineHero();
    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32, 32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

    }

    public void update(float dt){


    }


}
