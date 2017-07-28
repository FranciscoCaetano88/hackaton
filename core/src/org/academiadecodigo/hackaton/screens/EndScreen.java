package org.academiadecodigo.hackaton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.async.ThreadUtils;

import org.academiadecodigo.hackaton.GameEngine;

public class EndScreen implements Screen {

    private final GameEngine game;
    private Texture backGroundImage;
    private Music music;//TODO: music for initial screen, and if possible @Chiquinho uma animação

    public EndScreen(GameEngine game, String path) {
        this.game = game;
        this.backGroundImage = new Texture(Gdx.files.internal(path));
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

        // load the dropable sound effect and the rain background "music"
        music = Gdx.audio.newMusic(Gdx.files.internal("The Rolling Stones - Paint it black.mp3"));

        game.getBatch().begin();
        game.getBatch().draw(backGroundImage, 0, 0);
        game.getBatch().end();

        if(Gdx.input.isTouched()) {
            dispose();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.setScreen(new MainMenuScreen(game));
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
