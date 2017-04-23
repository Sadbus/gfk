package com.gfk.supermario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gfk.supermario.GameRenderer;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = GameRenderer.WIDTH;
		//config.height = GameRenderer.HEIGHT;
		config.title = GameRenderer.TITLE;
		new LwjglApplication(new GameRenderer(), config);
	}
}
