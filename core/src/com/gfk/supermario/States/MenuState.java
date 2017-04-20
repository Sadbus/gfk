package com.gfk.supermario.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gfk.supermario.SuperMario;

/**
 * Created by Nat on 20/04/2017.
 */
public class MenuState extends State
{
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        background = new Texture("MBG.jpg");
        playButton = new Texture("MSB.jpg");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(background, 0, 0, SuperMario.WIDTH, SuperMario.HEIGHT);
        sb.draw(playButton, (SuperMario.WIDTH/2) - (playButton.getWidth()/2), SuperMario.HEIGHT/2);
        sb.end();
    }
}
