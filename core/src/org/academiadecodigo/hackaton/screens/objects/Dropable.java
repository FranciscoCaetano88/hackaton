package org.academiadecodigo.hackaton.screens.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by codecadet on 27/07/2017.
 */

public  class Dropable {

    private Texture image;
    private Rectangle rectangle;

    public void create() {

        // create a Rectangle to logically represent the rectangle
        rectangle = new Rectangle();

        image = new Texture(Gdx.files.internal("cake.png"));
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
