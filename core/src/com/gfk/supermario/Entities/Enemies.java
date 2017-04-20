package com.gfk.supermario.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by olav on 20.04.17.
 */
public class Enemies {
    Sprite sprite;

    private int yPos;
    private int xPos;


    public Enemies(Sprite sprite){
        this.sprite = sprite;

    }

    public int getxPos(){
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }


}
