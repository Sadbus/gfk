package com.gfk.supermario.Utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gfk.supermario.Sprites.Blocks.*;
import com.gfk.supermario.Sprites.Items.Key;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by Olav on 23.04.2017.
 */
public class initWorld {

    public initWorld(GameScreen screen) {
        World world = screen.getWorld();
        TiledMap tiledMap = screen.getTiledMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //create tiles
        for (MapObject object : tiledMap.getLayers().get("Tiles").getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameRenderer.PPM,
                    (rect.getY() + rect.getHeight() / 2) / GameRenderer.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameRenderer.PPM,
                    rect.getHeight() / 2 / GameRenderer.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        //create coins
        for (MapObject object : tiledMap.getLayers().get("Coins").getObjects().getByType(RectangleMapObject.class)) {
            new Coin(screen, object);
        }

        //create Bricks
        for (MapObject object : tiledMap.getLayers().get("Bricks").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Box(screen, object);
        }

        //create Key
        for (MapObject object : tiledMap.getLayers().get("Key").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Key(world, tiledMap, rect);
        }

        //create Lock
        for (MapObject object : tiledMap.getLayers().get("Lock").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Lock(screen, object);
        }
    }
}
