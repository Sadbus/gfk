package com.gfk.supermario.Sprites.Items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Olav on 27.04.2017.
 */
public abstract class ItemObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    public ItemObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / GameRenderer.PPM, (bounds.getY() + bounds.getHeight() / 2) / GameRenderer.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / GameRenderer.PPM, bounds.getHeight() / 2 / GameRenderer.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Graphics");
        return  layer.getCell((int)(body.getPosition().x * GameRenderer.PPM / 21),
                (int)(body.getPosition().y * GameRenderer.PPM / 21));
    }

}
