package org.academiadecodigo.hackaton.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by codecadet on 28/07/17.
 */

public class Fog {

    private final int MIN_X = 610 - 480;

    private Texture texture;
    private SpriteBatch fogBatch;
    private Rectangle rectangle;

    private boolean movingRight;
    private int initialY;

    public Fog(int initialY) {

        this.initialY= initialY;

        movingRight = true;
        fogBatch = new SpriteBatch();
        fogBatch.setColor(Color.GRAY);
        rectangle = new Rectangle(-MIN_X,initialY,610,343);

        texture = new Texture(Gdx.files.internal("fog.png"));
        draw();
    }

    public void draw() {

        move();
        fogBatch.begin();
        fogBatch.draw(texture, rectangle.x , initialY);
        fogBatch.end();

    }

    public void move() {


        if (movingRight){
            moveRight();
        }
        else {
            moveLeft();
        }

        if (rectangle.x >= 0){
            movingRight = false;
        }

        if (rectangle.x <= -MIN_X){
            movingRight = true;
        }

    }


    public void moveLeft(){
        rectangle.x -= 50 * Gdx.graphics.getDeltaTime();
    }

    public void moveRight(){
        rectangle.x += 50 * Gdx.graphics.getDeltaTime();

    }

}
