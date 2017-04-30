package com.gfk.supermario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gfk.supermario.GameRenderer;

/**
 * Created by Adrian on 28.04.2017.
 */
public class OptionsScreen implements Screen {
    private GameRenderer game;
    protected Stage stage;
    private TextureAtlas atlas;
    private Skin skin;

    private Image background;
    private TextButton backButton;
    private ImageButton fullscreenCheckBox;
    private ImageButton vSyncCheckBox;

    private Table graphicsTable;
    private Table soundTable;

    private Slider musicVolumeSlider;
    private Slider soundVolumeSlider;

    Label musicVolumeValue;
    Label soundVolumeValue;

    public OptionsScreen(GameRenderer game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("UI.pack");
        skin = new Skin();
        skin.addRegions(atlas);

        buttonStyle();
        boxStyle();
        sliderStyle();

        // Create background image
        background = new Image(new Texture("menu_bg.png"));

        // skin refers to default textButtonStyle
        backButton = new TextButton("Go back", skin.get("button", TextButton.TextButtonStyle.class));

        //Constructing graphics table
        graphicsTable = new Table();

        fullscreenCheckBox = new ImageButton(skin.get("box", ImageButton.ImageButtonStyle.class));
        vSyncCheckBox = new ImageButton(skin.get("box", ImageButton.ImageButtonStyle.class));

        Label graphics = new Label("Graphics:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label fullscreen = new Label(" Fullscreen", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label vSync = new Label(" vSync", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        graphicsTable.add(graphics);
        graphicsTable.row().pad(10);
        graphicsTable.add(fullscreenCheckBox);
        graphicsTable.add(fullscreen);
        graphicsTable.row().pad(5);
        graphicsTable.add(vSyncCheckBox);
        graphicsTable.add(vSync);

        //Constructing sound table
        soundTable = new Table();
        Label volume = new Label("Volume:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label musicVolume = new Label("Music ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label soundVolume = new Label("Sound ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        musicVolumeSlider = new Slider(0.1f, 0.9f, 0.1f, false, skin.get("slider", Slider.SliderStyle.class));
        soundVolumeSlider = new Slider(0.1f, 0.9f, 0.1f, false, skin.get("slider", Slider.SliderStyle.class));

        musicVolumeSlider.setValue(game.musicVolume);
        soundVolumeSlider.setValue(game.soundVolume);

        musicVolumeValue = new Label("0.9", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        soundVolumeValue = new Label("0.9", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        soundTable.add(volume);
        soundTable.row().pad(10);
        soundTable.add(musicVolume);
        soundTable.add(musicVolumeSlider);
        soundTable.add(musicVolumeValue);
        soundTable.row().pad(5);
        soundTable.add(soundVolume);
        soundTable.add(soundVolumeSlider);
        soundTable.add(soundVolumeValue);
    }

    @Override
    public void show() {
        // Create ClickListeners for buttons
        fullscreenCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                if (fullscreenCheckBox.isChecked()){
                    System.out.println("Switching to fullscreen");
                    if (!Gdx.graphics.isFullscreen()){
                        game.prefs.putBoolean("fullscreen", true);
                        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    }
                } else {
                    System.out.println("Switching to Windowed");
                    if (Gdx.graphics.isFullscreen()){
                        game.prefs.putBoolean("fullscreen", false);
                        Gdx.graphics.setWindowedMode(1024, 576);
                    }
                }
            }
        });
        vSyncCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                if (vSyncCheckBox.isChecked()){
                    game.prefs.putBoolean("vSync", true);
                    Gdx.graphics.setVSync(true);
                } else {
                    game.prefs.putBoolean("vSync", false);
                    Gdx.graphics.setVSync(false);
                }
            }
        });
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //game.musicVolume = musicVolumeSlider.getValue();
                //musicVolumeValue.setText("" + game.musicVolume);

                game.prefs.putFloat("musicVolume", musicVolumeSlider.getValue());
                musicVolumeValue.setText("" + game.prefs.getFloat("musicVolume"));
            }
        });
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.soundVolume = soundVolumeSlider.getValue();
                soundVolumeValue.setText("" + game.soundVolume);
            }
        });

        if(game.prefs.getBoolean("fullscreen")){
            fullscreenCheckBox.setChecked(true);
        }
        if(game.prefs.getBoolean("vSync")) {
            vSyncCheckBox.setChecked(true);
        }

        backButton.setPosition(Gdx.graphics.getWidth() /2 - backButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 10);
        graphicsTable.setPosition(Gdx.graphics.getWidth() / 5 - graphicsTable.getWidth() / 2,
                Gdx.graphics.getHeight() / 1.4f - graphicsTable.getHeight() / 2);
        soundTable.setPosition(Gdx.graphics.getWidth() / 2 - graphicsTable.getWidth() / 2,
                Gdx.graphics.getHeight() / 1.4f - graphicsTable.getHeight() / 2);
        background.setPosition(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);


        musicVolumeValue.setText("" + game.prefs.getFloat("musicVolume"));
        soundVolumeValue.setText("" + game.soundVolume);

        stage.addActor(background);
        stage.addActor(backButton);
        stage.addActor(graphicsTable);
        stage.addActor(soundTable);


        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameRenderer.manager.get("audio/sounds/menu_click.mp3", Sound.class).play();
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    private void sliderStyle() {
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = skin.getDrawable("slider");
        sliderStyle.knob = skin.getDrawable("sliderLeft");
        skin.add("slider", sliderStyle);

    }

    private void boxStyle() {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("box");
        imageButtonStyle.down = skin.getDrawable("boxCheckmark");
        imageButtonStyle.checked = skin.getDrawable("boxCheckmark");
        skin.add("box", imageButtonStyle);
    }

    private void buttonStyle() {
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button05");
        skin.add("button", textButtonStyle);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        atlas.dispose();
        game.dispose();
    }
}
