package com.gfk.supermario.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

public class Ghost extends Enemy
{

    private float stateTime;
    private Animation floatAnimation;
    private Array<TextureRegion> frames;
    private boolean enemyDie;
    private boolean kill;

    public Ghost(GameScreen screen, float x, float y)
    {
        super(screen, x, y);

        frames = new Array<TextureRegion>();
        for(int i = 0; i < 14; i++)
        {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("13"), 0, 0, 17, 21));
        }

        floatAnimation = new Animation(0.5f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 17/ GameRenderer.PPM, 21/GameRenderer.PPM);

        enemyDie = false;
        kill = false;
    }

    public void update(float dt)
    {
        stateTime += dt;
        if(enemyDie && !kill)
        {
            world.destroyBody(b2body);
            kill = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("15"), 0, 0, 17, 21));
            stateTime = 0;
        }
        else if(!kill)
        {
            b2body.setLinearVelocity(floating);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((TextureRegion) floatAnimation.getKeyFrame(stateTime, true));
        }
    }

    @Override
    protected void defineEnemy()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(50/GameRenderer.PPM, 50/GameRenderer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / GameRenderer.PPM);
        fixtureDef.friction = 0.6f;
        fixtureDef.filter.categoryBits = GameRenderer.ENEMY_BIT;
        fixtureDef.filter.maskBits = GameRenderer.DEFAULT_BIT |
                GameRenderer.COIN_BIT |
                GameRenderer.BOX_BIT |
                GameRenderer.KEY_BIT |
                GameRenderer.LOCK_BIT |
                GameRenderer.MOVABLE_TILE_BIT |
                GameRenderer.ENEMY_BIT |
                GameRenderer.DEFAULT_BIT |
                GameRenderer.HERO_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

        // Definerer sensor for å sjekke kollisjon med hodet
        PolygonShape ghostHead = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-5, 8).scl(1 / GameRenderer.PPM);
        vertice[1] = new Vector2(5, 8).scl(1 / GameRenderer.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / GameRenderer.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / GameRenderer.PPM);
        ghostHead.set(vertice);

        fixtureDef.shape = ghostHead;
        fixtureDef.restitution = 0.5f;
        fixtureDef.filter.categoryBits = GameRenderer.ENEMY_HEAD_BIT;
        b2body.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void hitOnHead()
    {
        enemyDie = true;
    }

    public void draw(Batch batch)
    {
        if (!kill || stateTime < 1)
        {
            super.draw(batch);
        }
    }

}
