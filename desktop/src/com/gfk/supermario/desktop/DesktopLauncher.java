package com.gfk.supermario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gfk.supermario.SuperMario;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SuperMario.WIDTH;
		config.height = SuperMario.HEIGHT;
		config.title = SuperMario.TITLE;
		new LwjglApplication(new SuperMario(), config);
	}
}
