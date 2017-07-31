package org.academiadecodigo.hackaton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import org.academiadecodigo.hackaton.GameEngine;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;
import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_Y;

public class EndScreen implements Screen {
    private Texture nextMessage;

    private final GameEngine game;
    private Texture backGroundImage;
    private Music music;//TODO: music for initial screen, and if possible @Chiquinho uma animação

    public EndScreen(GameEngine game) {
        this.game = game;
        nextMessage = new Texture(Gdx.files.internal("play.png"));
    }

    public void setBackGroundImage(Texture backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       music.setLooping(true);
        music.play();

        game.getBatch().begin();
        game.getBatch().draw(backGroundImage, 0, 0);
        game.getBatch().end();

        game.getBatch().begin();
        game.getBatch().draw(nextMessage, (int) (SCREEN_SIZE_X * 0.85), (int) (SCREEN_SIZE_Y * 0.1));
        game.getBatch().end();


        if (Gdx.input.isTouched()) {

            if (Gdx.input.getX(0) > SCREEN_SIZE_X * 0.85 &&
                    Gdx.input.getY(0) > SCREEN_SIZE_Y * 0.85 &&
                    Gdx.input.getY(0) < SCREEN_SIZE_Y * 0.95) {
                dispose();
                game.setScreen(new GameScreen(game));
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

    }
}
