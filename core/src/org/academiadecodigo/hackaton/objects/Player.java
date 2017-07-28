package org.academiadecodigo.hackaton.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;


/**
 * Created by codecadet on 27/07/2017.
 */

public class Player {

    private Texture texture;
    private Rectangle rectangle;
    private boolean up = true;

    public Player() {

        changeImage();

        // create a Rectangle to logically represent the rectangle
        rectangle = new Rectangle();
        //rectangle.setX()
        rectangle.x = SCREEN_SIZE_X / 2 - 64 / 2; // center the rectangle horizontally
        rectangle.y = 20; // bottom left corner of the rectangle is 20 pixels above the bottom screen edge
        rectangle.width = (int)(64 * 1.5);
        rectangle.height = (int)(64 * 1.5);
    }

    public Texture getImage() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void changeImage() {

        if (up) {
            texture = new Texture(Gdx.files.internal("player.png"));
            up = false;
            return;
        }

        texture = new Texture(Gdx.files.internal("player_move.png"));
        up = true;


    }
}
