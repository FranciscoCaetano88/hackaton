package org.academiadecodigo.hackaton.screens.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by codecadet on 27/07/2017.
 */

public class Drop {

    private Texture dropImage;
    private Rectangle drop;

    public void create() {

        // create a Rectangle to logically represent the drop
        drop = new Rectangle();

        dropImage = new Texture(Gdx.files.internal("droplet.png"));
    }


    public Texture getDropImage() {
        return dropImage;
    }

    public void setDropImage(Texture dropImage) {
        this.dropImage = dropImage;
    }

    public Rectangle getRectangle() {
        return drop;
    }

    public void setDrop(Rectangle drop) {
        this.drop = drop;
    }
}
