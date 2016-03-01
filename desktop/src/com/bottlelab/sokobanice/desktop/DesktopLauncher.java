package com.bottlelab.sokobanice.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bottlelab.sokobanice.GameManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.foregroundFPS = 150;
		new LwjglApplication(new GameManager(new DestopAdsController()), config);
	}
}
