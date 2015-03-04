package com.nonatomicgames.chinger.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nonatomicgames.chinger.ChingerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // Sega Mega Drive sizes * 2;
        config.width = 320 * 2;
        config.height = 240 * 2;

		new LwjglApplication(new ChingerGame(), config);
	}
}
