package org.academiadecodigo.hackaton.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.academiadecodigo.hackaton.GameEngine;
import org.academiadecodigo.hackaton.objects.text.MessageFactory;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "GameEngine";
		config.width = 480;
		config.height = 800;

		new LwjglApplication(new GameEngine(config.width, config.height), config);
	}
}
