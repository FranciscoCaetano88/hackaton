package org.academiadecodigo.hackaton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.academiadecodigo.hackaton.objects.text.MessageFactory;
import org.academiadecodigo.hackaton.screens.MainMenuScreen;

public class GameEngine extends Game {
	private BitmapFont font;
	private SpriteBatch batch;

	public static int WIDTH;
	public static int HEIGHT;

	public GameEngine() {
		this(480, 800);
	}

	public GameEngine(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		font = new BitmapFont();

		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

	public BitmapFont getFont() {
		return font;
	}

	public SpriteBatch getBatch() {
		batch.setColor(1,1,1,1);
		return batch;
	}

}
