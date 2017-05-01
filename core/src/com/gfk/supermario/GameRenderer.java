package com.gfk.supermario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gfk.supermario.Screens.GameScreen;
import com.gfk.supermario.Screens.MenuScreen;

public class GameRenderer extends Game {
    //Tile bredde * antall tiles i høyden
    public static final int HEIGHT = 260;
    //Bredde skal være slik at sideforholdet blir 16:9
    public static final int WIDTH = 462;

    //"Pixels per Meter", brukes for skalering.
    public static final float PPM = 100;

    public static final short NOTHING_BIT = 0;
    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short HERO_HEAD_BIT = 4;
    public static final short BOX_BIT = 8;
    public static final short COIN_BIT = 16;
    public static final short DESTROYED_BIT = 32;
    public static final short KEY_BIT = 64;
    public static final short LOCK_BIT = 128;
    public static final short DEATH_BIT = 256;
    public static final short ENEMY_BIT = 512;
    public static final short ENEMY_HEAD_BIT = 1024;
    public static final short ENEMY_COLLISION_BIT = 2048;

    //Variables for OptionScreen
    public static float musicVolume = 1f;
    public static float soundVolume = 1f;

    public static final String TITLE = "Key Master; The quest for keys";

    public SpriteBatch batch;

    public static AssetManager manager;

    public static Preferences prefs;



    @Override
    public void create () {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/music/game_music.mp3", Music.class);
        manager.load("audio/music/menu_music.mp3", Music.class);
        manager.load("audio/sounds/break_box.ogg", Sound.class);
        manager.load("audio/sounds/coin.mp3", Sound.class);
        manager.load("audio/sounds/key.mp3", Sound.class);
        manager.load("audio/sounds/menu_click.mp3", Sound.class);
        manager.load("audio/sounds/lock.mp3", Sound.class);
        manager.load("audio/sounds/hero_die.mp3", Sound.class);
        manager.load("audio/sounds/spider_die.wav", Sound.class);
        manager.load("audio/sounds/ghost_die.mp3", Sound.class);
        manager.finishLoading();

        prefs = Gdx.app.getPreferences("My Preferences");

        prefs.putBoolean("fullscreen", false);
        prefs.putBoolean("vSync", false);
        prefs.putFloat("soundVolume", 1f);
        prefs.putFloat("musicVolume", 1f);
        prefs.putBoolean("soundOn", true);
        prefs.putBoolean("musicOn", true);
        prefs.putInteger("level", 1);

        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        manager.dispose();
    }
}