package com.gfk.supermario.Entities;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gfk.supermario.GameRenderer;
import com.gfk.supermario.Screens.GameScreen;

/**
 * Created by olav on 20.04.17.
 */
public class Hero extends Sprite{
    private int health;
    private int yPos;
    private int xPos;
    private int xSpeed;
    private int ySpeed;

    private GameScreen screen;


    public Hero(GameScreen screen){
    this.screen = screen;
    }

    public void update(float dt){


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
