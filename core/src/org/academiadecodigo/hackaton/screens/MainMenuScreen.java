package org.academiadecodigo.hackaton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import org.academiadecodigo.hackaton.GameEngine;
import org.academiadecodigo.hackaton.objects.text.Message;
import org.academiadecodigo.hackaton.objects.text.MessageFactory;

public class MainMenuScreen implements Screen {

    final GameEngine gameEngine;

    Texture texture;

    OrthographicCamera camera;

    public MainMenuScreen(final GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameEngine.WIDTH, GameEngine.HEIGHT);

        texture = new Texture(Gdx.files.internal("beg_screen.jpg"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        gameEngine.getBatch().setProjectionMatrix(camera.combined);

        gameEngine.getBatch().begin();
        gameEngine.getBatch().draw(texture, 0, 0);
        gameEngine.getBatch().end();


        if (Gdx.input.isTouched()) {
            gameEngine.setScreen(new GameScreen(gameEngine));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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
        texture.dispose();
    }
}
