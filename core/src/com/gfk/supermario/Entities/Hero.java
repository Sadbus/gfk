package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by olav on 20.04.17.
 */
public class Hero {
    private Sprite sprite;

    private int health;
    private int yPos;
    private int xPos;
    private int xSpeed;
    private int ySpeed;


    public Hero(Sprite sprite){
        this.sprite = sprite;

    }

    public void update(float delta){

    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void jump(){

    }

    public void moveForward(){
        xPos++;
    }

    public void moveBackward(){
        xPos--;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
