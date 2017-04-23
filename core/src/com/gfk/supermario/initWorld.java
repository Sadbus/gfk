package com.gfk.supermario;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Olav on 23.04.2017.
 */
public class initWorld {

    public initWorld(GameScreen screen){
        /*
        World world = screen.getWorld();
        TiledMap tiledMap = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Create ground
        for(MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //create pipe
        for (MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //create brick
        for (MapObject object : tiledMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //create coin
        for (MapObject object : tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM, (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM, rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        */

    }
}
