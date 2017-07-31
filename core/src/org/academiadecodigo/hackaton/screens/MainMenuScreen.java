package org.academiadecodigo.hackaton.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.academiadecodigo.hackaton.GameEngine;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;
import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_Y;

public class MainMenuScreen implements Screen {

    private final GameEngine gameEngine;

    private Texture nextMessage;
    private Texture background;
    private Texture glassEffect;
    private Music music;

    private SpriteBatch glassBatch;

    private OrthographicCamera camera;

    public MainMenuScreen(final GameEngine gameEngine) {

        this.gameEngine = gameEngine;

        glassBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameEngine.WIDTH, GameEngine.HEIGHT);

        background = new Texture(Gdx.files.internal("beg_screen.jpg"));
        glassEffect = new Texture(Gdx.files.internal("glass_effect.jpg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        nextMessage = new Texture(Gdx.files.internal("play.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // start the playback of the background music immediately
        music.setLooping(true);
        music.play();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        gameEngine.getBatch().setProjectionMatrix(camera.combined);

        gameEngine.getBatch().begin();
        gameEngine.getBatch().draw(background, 0, 0);
        gameEngine.getBatch().end();

        gameEngine.getBatch().begin();
        gameEngine.getBatch().draw(nextMessage, (int) (SCREEN_SIZE_X * 0.85), (int) (SCREEN_SIZE_Y * 0.1));
        gameEngine.getBatch().end();


        glassBatch.begin();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            glassBatch.setColor(1f, 1f, 1f, 0.25f);
            glassBatch.draw(glassEffect, 0, 0);
        }
        glassBatch.end();

        if (Gdx.input.isTouched()) {

            //System.out.println(Gdx.input.getX(0) + " > " + SCREEN_SIZE_X * 0.85 + "   " + Gdx.input.getY(0) + " > " + SCREEN_SIZE_Y * 0.85 + " < " + (SCREEN_SIZE_Y * 0.9));

            if (Gdx.input.getX(0) > SCREEN_SIZE_X * 0.85 &&
                    Gdx.input.getY(0) > SCREEN_SIZE_Y * 0.85 &&
                    Gdx.input.getY(0) < SCREEN_SIZE_Y * 0.95) {
                dispose();
                gameEngine.setScreen(new GameScreen(gameEngine));
            }

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
        background.dispose();
        music.dispose();
    }
}
