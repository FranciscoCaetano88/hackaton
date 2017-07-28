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
    private Texture doorImage;

    public Score() {
        score = SCREEN_SIZE_X / 4;

        doorImage = new Texture(Gdx.files.internal("beg_screen.jpg"));

        shapeRenderer = new ShapeRenderer();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(5, 10, score, 20);
        shapeRenderer.end();
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {

        if (score > SCREEN_SIZE_X / 2) {
            return;
        }

        score += 10;
    }

    public void updateScoreBar() {



        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(5, 10, score, 50);
        shapeRenderer.end();
    }


    public void decrementScore() {

        if (score < 0) {
            return;
        }

        score -= 10;

        if (score < 0) {
            score = 0;
        }
    }
}
