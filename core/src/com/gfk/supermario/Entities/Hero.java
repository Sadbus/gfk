package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

import javax.swing.plaf.nimbus.State;

/**
 * Created by olav on 20.04.17.
 */
public class Hero extends Sprite
{
    public World world;
    public Body b2body;
    public enum State
    {
        FALLING, JUMPING, STANDING, RUNNING
    };
    public State currentState;
    public State previousState;
    private Animation heroRun;
    private Animation heroJump;

    private float stateTimer;
    private boolean runningRight;

    public static Boolean hasKey;

    private TextureRegion heroStand;

    public Hero(World world, GameScreen screen)
    {
        super(screen.getAtlas().findRegion("Still"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 1; i++)
        {
            frames.add(new TextureRegion(getTexture(), i * 24, 0, 20, 24 ));
        }
        heroRun = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 2; i < 3; i++)
        {
            frames.add(new TextureRegion(getTexture(), i*24, 0, 20, 24));
        }
        heroJump = new Animation(0.1f, frames);


        heroStand = new TextureRegion(getTexture(), 133,1,20,24);
        defineHero();
        setBounds(5,0, 20/ GameRenderer.PPM, 24/GameRenderer.PPM);
        setRegion(heroStand);

        hasKey = false;
    }


    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt)
    {
        currentState = getState();
        TextureRegion region;
        switch (currentState)
        {
            case JUMPING:
            {
                region = (TextureRegion) heroJump.getKeyFrame(stateTimer);
                break;
            }
            case RUNNING:
            {
                region = (TextureRegion) heroRun.getKeyFrame(stateTimer, true);
                break;
            }
            case FALLING:
            case STANDING:
            default:
            {
                region = heroStand;
                break;
            }
        }

        // Going left or right

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
        {
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState()
    {
        if (b2body.getLinearVelocity().y > 0) //|| (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
        {
            return State.JUMPING;
        }
        else if (b2body.getLinearVelocity().y < 0)
        {
            return State.FALLING;
        }
        else if (b2body.getLinearVelocity().x != 0)
        {
            return State.RUNNING;
        }
        else
        {
            return State.STANDING;
        }
    }

    public void defineHero()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameRenderer.PPM, 32 / GameRenderer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / GameRenderer.PPM);
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

        /*
        // Definerer sensor for å sjekke kollisjon med hodet
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / GameRenderer.PPM, 6 / GameRenderer.PPM), new Vector2(2 / GameRenderer.PPM, 6 / GameRenderer.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        b2body.createFixture(fixtureDef).setUserData("head");


        //definerer sensor for å sjekke kollisjon med kroppen
        EdgeShape bodyShape = new EdgeShape();
        bodyShape.setRadius(6 / GameRenderer.PPM);
        fixtureDef.shape = bodyShape;
        fixtureDef.isSensor = true;
        */

        b2body.createFixture(fixtureDef).setUserData("bodyShape");

    }

    public void draw(Batch batch)
    {
        super.draw(batch);
    }
}