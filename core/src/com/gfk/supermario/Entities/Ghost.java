package com.gfk.supermario.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

public class Ghost extends Enemy
{

    private float stateTime;
    private Animation floatAnimation;
    private Array<TextureRegion> frames;
    public Ghost(GameScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("8"), i * 15, 0, 15, 8 ));
        floatAnimation = new Animation(0.5f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 15 / GameRenderer.PPM, 8 / GameRenderer.PPM);
    }

    public void update(float dt)
    {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
        setRegion(floatAnimation.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineEnemy()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameRenderer.PPM, 32 / GameRenderer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / GameRenderer.PPM);
        fixtureDef.friction = 0.6f;
        fixtureDef.filter.categoryBits = GameRenderer.ENEMY_BIT;
        fixtureDef.filter.maskBits = GameRenderer.GROUND_BIT |
                GameRenderer.COIN_BIT |
                GameRenderer.BRICK_BIT |
                GameRenderer.KEY_BIT |
                GameRenderer.LOCK_BIT |
                GameRenderer.MOVABLE_TILE_BIT |
                GameRenderer.ENEMY_BIT |
                GameRenderer.OBJECT_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

        // Definerer sensor for å sjekke kollisjon med hodet
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / GameRenderer.PPM, 10 / GameRenderer.PPM), new Vector2(2 / GameRenderer.PPM, 10 / GameRenderer.PPM));
        fixtureDef.filter.categoryBits = GameRenderer.HERO_HEAD_BIT;
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        b2body.createFixture(fixtureDef).setUserData("head");



        b2body.createFixture(fixtureDef).setUserData("bodyShape");

    }
}
