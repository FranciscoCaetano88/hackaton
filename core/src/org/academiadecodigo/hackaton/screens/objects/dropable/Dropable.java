package org.academiadecodigo.hackaton.screens.objects.dropable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Dropable {

    private Texture happyDropableImage;
    private Texture depressedDropableImage;

    private Rectangle dropableRectangle;

    private boolean depressed = false;

    public Texture getHappyDropableImage() {
        return depressed ? depressedDropableImage : happyDropableImage;
    }

    public void setHappyDropableImage(Texture dropImage) {
        this.happyDropableImage = dropImage;
    }

    public Rectangle getDropableRectangle() {
        return dropableRectangle;
    }

    public void setDropableRectangle(Rectangle dropableRectangle) {
        this.dropableRectangle = dropableRectangle;
    }

    public void setDepressedDropableImage(Texture depressedDropableImage) {
        this.depressedDropableImage = depressedDropableImage;
    }
}
