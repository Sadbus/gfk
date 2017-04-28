package com.gfk.supermario.Entities;

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
public class Hero extends Sprite
{
    public World world;
    public Body b2body;

    public static Boolean hasKey;

    private TextureRegion heroStand;
    public Hero(World world, GameScreen screen)
    {
        super(screen.getAtlas().findRegion("Still"));
        this.world = world;
        defineHero();
        heroStand = new TextureRegion(getTexture(), 0,0,24,24);
        setBounds(0,0, 24 / GameRenderer.PPM, 24 / GameRenderer.PPM);
        setRegion(heroStand);

        hasKey = false;
    }


    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }

    public void defineHero()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameRenderer.PPM, 32 / GameRenderer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.4f;
        CircleShape shape = new CircleShape();
        shape.setRadius(9 / GameRenderer.PPM);
        fixtureDef.filter.categoryBits = GameRenderer.HERO_BIT;
        fixtureDef.filter.maskBits = GameRenderer.DEFAULT_BIT |
                GameRenderer.COIN_BIT |
                GameRenderer.BRICK_BIT |
                GameRenderer.KEY_BIT |
                GameRenderer.LOCK_BIT |
                GameRenderer.MOVABLE_TILE_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

        // Definerer sensor for å sjekke kollisjon med hodet
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / GameRenderer.PPM, 10 / GameRenderer.PPM), new Vector2(2 / GameRenderer.PPM, 10 / GameRenderer.PPM));
        fixtureDef.filter.categoryBits = GameRenderer.HERO_HEAD_BIT;
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        b2body.createFixture(fixtureDef).setUserData("head");
    }

    public void draw(Batch batch)
    {
        super.draw(batch);

    }
}