package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by olav on 20.04.17.
 */
public class Hero extends Sprite{
    public World world;
    public Body b2body;
    private GameRenderer game;

    private TextureRegion region;
    private Texture texture;

    private GameScreen screen;

    public Hero(World world)
    {
    this.world = world;
    defineHero();

    texture = new Texture("region.jpeg");
    region = new TextureRegion(texture, 20, 20, 50, 50);
    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameRenderer.PPM, 32 / GameRenderer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameRenderer.PPM);
        fixtureDef.filter.categoryBits = GameRenderer.HERO_BIT;
        fixtureDef.filter.maskBits = GameRenderer.DEFAULT_BIT | GameRenderer.COIN_BIT | GameRenderer.BRICK_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / GameRenderer.PPM, 6 / GameRenderer.PPM), new Vector2(2 / GameRenderer.PPM, 6 / GameRenderer.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        b2body.createFixture(fixtureDef).setUserData("head");
    }

    public void update(float dt){


    }

    public void draw(Batch batch){
        //super.draw(batch);
        batch.begin();
        //batch.draw(img, 32 / GameRenderer.PPM, 32 / GameRenderer.PPM);
        batch.draw(region, 10, 10);
        batch.end();
    }

}
