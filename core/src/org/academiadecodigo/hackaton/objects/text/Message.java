package org.academiadecodigo.hackaton.objects.text;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Desktop;

public class Message extends Actor {

    private String message;

    private static final float FADE_IN_TIME = 1f;
    private float fadeElapsed = 0;

    public Message(String message) {
        this.message = message;
    }

    public void draw(SpriteBatch batch, BitmapFont font, float delta, int x, int y) {

        if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            font.getData().setScale(1.2f);
        } else {
            font.getData().setScale(1.5f);
        }

        fadeElapsed += delta;
        float fade = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);

        font.setColor(1, 1, 1, fade);
        font.draw(batch, message, x, y, 400, 20, true);
    }

}
