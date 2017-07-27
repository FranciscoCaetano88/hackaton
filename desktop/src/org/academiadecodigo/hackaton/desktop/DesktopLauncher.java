package org.academiadecodigo.hackaton.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.hackaton.ScreenManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "ScreenManager";
		config.width = 800;
		config.height = 480;

		new LwjglApplication(new ScreenManager(), config);
	}
}
