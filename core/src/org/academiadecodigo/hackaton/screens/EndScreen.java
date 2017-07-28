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

    public EndScreen(GameEngine game) {
        this.game = game;
        init();
    }

    private void init() {

        // load the images for the droplet and the player, 64x64 pixels each
        backGroundImage = new Texture(Gdx.files.internal("game_background.jpg"));


        // load the dropable sound effect and the rain background "music"
        music = Gdx.audio.newMusic(Gdx.files.internal("The Rolling Stones - Paint it black.mp3"));

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
