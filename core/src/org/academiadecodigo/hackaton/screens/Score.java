package org.academiadecodigo.hackaton.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;

/**
 * Created by codecadet on 27/07/2017.
 */

public class Score {

    private int score;

    private ShapeRenderer shapeRenderer;

    public Score() {
        score = SCREEN_SIZE_X / 4;

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

        if(score<0){
            score=0;
        }
    }
}
