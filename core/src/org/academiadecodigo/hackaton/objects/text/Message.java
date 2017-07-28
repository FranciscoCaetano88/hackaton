package org.academiadecodigo.hackaton.objects.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Message extends Actor {

    private String message;

    private static final float FADE_IN_TIME = 1f;
    private float fadeElapsed = 0;

    public Message(String message) {
        this.message = message;
    }

    public void draw(SpriteBatch batch, BitmapFont font, float delta) {

        font.getData().setScale(1.5f);

        fadeElapsed += delta;
        float fade = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);

        font.setColor(0, 0, 0, fade);
        font.draw(batch, message, 20, (780 / 2) - (font.getLineHeight() / 2), 440, 10, true);
    }

}
