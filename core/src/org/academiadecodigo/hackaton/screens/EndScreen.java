package org.academiadecodigo.hackaton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import org.academiadecodigo.hackaton.GameEngine;

/**
 * Created by codecadet on 28/07/2017.
 */

public class EndScreen implements Screen {


    private final GameEngine game;
    private Texture backGroundImage;
    private Sound dropSound;
    private Music music;

    public EndScreen(GameEngine game, String path) {
        this.game = game;
        this.backGroundImage= new Texture(Gdx.files.internal(path));
        init();
    }

    private void init() {

        // load the dropable sound effect and the rain background "music"
        music = Gdx.audio.newMusic(Gdx.files.internal("The Rolling Stones - Paint it black.mp3"));

        game.getBatch().begin();
        game.getBatch().draw(backGroundImage,0,0);
        game.getBatch().end();

    }








    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
