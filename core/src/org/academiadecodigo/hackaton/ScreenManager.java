package org.academiadecodigo.hackaton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager extends Game {
	BitmapFont font;
	SpriteBatch batch;

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
}
