package org.academiadecodigo.hackaton.objects.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;

/**
 * Created by codecadet on 27/07/2017.
 */

public class Score {

    private int score;
    private SpriteBatch doorBatch;
    private ShapeRenderer shapeRenderer;
    private Texture doorTexture;
    private float brightness;

    public Score() {
        brightness = 0.5F;
        score = SCREEN_SIZE_X / 8;

        doorTexture = new Texture(Gdx.files.internal("red_door.jpg"));
        doorBatch.begin();
        doorBatch.setColor(0.5f, 0.5f, 0.5f, brightness);
        doorBatch.end();

    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {

        if (score > SCREEN_SIZE_X / 2) {
            return;
        }

        brightness += 0.1F;

        score += 10;
    }

    public void updateScoreBar() {


        doorBatch.begin();
        doorBatch.setColor(0.5f, 0.5f, 0.5f, brightness);
        doorBatch.end();
    }


    public void decrementScore() {

        if (score < 0) {
            return;
        }

        brightness -= 0.1F;

        score -= 10;

        if (score < 0) {
            score = 0;
        }
    }

    public void dispose() {

    }
}
