package com.gfk.supermario.Sprites.Blocks;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by olav on 20.04.17.
 */
public class MovableTile extends Sprite
{
    public World world;
    public Body body;
    private float x;
    private float y;

    public MovableTile(GameScreen screen, float x, float y) {
        super(screen.getAtlas().findRegion("7"));
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        defineBox();
        //TODO: Switch texture
        setRegion(new TextureRegion(getTexture(), 133,4,21,21));
        setBounds(0,0, 21 / GameRenderer.PPM, 21 / GameRenderer.PPM);
    }


    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
    }

    public void defineBox() {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.position.set(x / GameRenderer.PPM, y / GameRenderer.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        shape.setAsBox(21 / 2 / GameRenderer.PPM, 21 / 2 / GameRenderer.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

    }

    public void draw(Batch batch) {
        super.draw(batch);

    }
}