package com.gfk.supermario.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

import static com.gfk.supermario.Entities.Hero.State.*;

/**
 * Created by olav on 20.04.17.
 */
public class Hero extends Sprite
{
    public World world;
    public Body b2body;
    public enum State{FALLING, JUMPING, STANDING, RUNNING, DEAD}
    public State currentState;
    public State previousState;
    public State fallingState;
    private Animation<TextureRegion> heroRun;
    private Animation<TextureRegion> heroJump;
    private Animation<TextureRegion> heroFall;

    private float stateTimer;

    private boolean runningRight;
    public static Boolean hasKey;
    public static Boolean isHit;
    public static Boolean isDead;

    private TextureRegion heroStand;
    private TextureRegion heroDead;

    public Hero(GameScreen screen)
    {
        super(screen.getAtlas().findRegion("1"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        fallingState = State.FALLING;
        stateTimer = 0;
        runningRight = true;
        isDead = false;
        isHit = false;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++)
        {
            frames.add(new TextureRegion(getTexture(), i * 23, 7, 20, 24 ));
        }
        heroRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 3; i <5; i++)
        {
            frames.add(new TextureRegion(getTexture(), 24, 7, 20, 24));
        }
        heroJump = new Animation<TextureRegion>(0.2f, frames);

        for (int i = 4; i < 6; i++)
        {
            frames.add(new TextureRegion(getTexture(), 24, 7, 20, 24 ));
        }
        heroFall = new Animation<TextureRegion>(0.1f, frames);


        heroStand = new TextureRegion(getTexture(), 1,7,20,24);
        heroDead = new TextureRegion(screen.getAtlas().findRegion("5"), 24, 7, 20, 20);

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
                region = heroJump.getKeyFrame(stateTimer);
                break;
            }
            case RUNNING:
            {
                region = heroRun.getKeyFrame(stateTimer, true);
                break;
            }
            case FALLING:
            case STANDING:
            default:
            {
                region = heroStand;
                break;
            }
            case DEAD:
                region = heroDead;
                break;
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
        if (b2body.getLinearVelocity().y > 0 )//|| (b2body.getLinearVelocity().y < 0 && currentState == State.JUMPING))
        {
            return JUMPING;
        }
        else if (b2body.getLinearVelocity().y < 0 && currentState == JUMPING)
        {
            return State.FALLING;
        }
        else if (b2body.getLinearVelocity().x != 0)
        {
            return State.RUNNING;
        }
        else if (isHit) {
            hit();
            return State.DEAD;
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
        fixtureDef.friction = 0.6f;
        fixtureDef.filter.categoryBits = GameRenderer.HERO_BIT;
        fixtureDef.filter.maskBits = GameRenderer.DEFAULT_BIT |
                GameRenderer.COIN_BIT |
                GameRenderer.BOX_BIT |
                GameRenderer.KEY_BIT |
                GameRenderer.LOCK_BIT |
                GameRenderer.ENEMY_BIT |
                GameRenderer.DEATH_BIT |
                GameRenderer.ENEMY_HEAD_BIT;

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


    public void jump(){
        if (currentState == STANDING | currentState == RUNNING){
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }

    public void hit(){
        if (!isDead) {

            Gdx.app.log("Hero", "is hit");
            GameRenderer.manager.get("audio/music/game_music.mp3", Music.class).stop();
            GameRenderer.manager.get("audio/sounds/hero_die.mp3", Sound.class).play(GameRenderer.prefs.getFloat("soundVolume"));
            Filter filter = new Filter();
            filter.maskBits = GameRenderer.NOTHING_BIT;
            for(Fixture fixture : b2body.getFixtureList()){
                fixture.setFilterData(filter);
            }
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            isDead = true;
        }
    }

    public void draw(Batch batch) {super.draw(batch);}

    public boolean isDead(){
        return isDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }
}