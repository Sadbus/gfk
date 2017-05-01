package com.gfk.supermario.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gfk.supermario.GameRenderer;
import org.lwjgl.opengl.ATIEnvmapBumpmap;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.addIcon("pepe.png", Files.FileType.Internal);
		config.width = GameRenderer.WIDTH;
		config.height = GameRenderer.HEIGHT;
		config.title = GameRenderer.TITLE;
		config.width = 1024;
		config.height = 576;
		config.forceExit = false;
		config.resizable = false;
		new LwjglApplication(new GameRenderer(), config);
	}
}
