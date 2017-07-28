package org.academiadecodigo.hackaton.objects.dropable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Dropable {

    private Texture happyDropableImage;
    private Texture depressedDropableImage;

    private Rectangle rectangle;

    private boolean depressed = false;

    public Texture getImage() {
        return depressed ? depressedDropableImage : happyDropableImage;
    }

    public void setHappyDropableImage(Texture dropImage) {
        this.happyDropableImage = dropImage;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setDepressedDropableImage(Texture depressedDropableImage) {
        this.depressedDropableImage = depressedDropableImage;
    }

    public boolean isDepressed() {
        return depressed;
    }

    public void setDepressed(boolean depressed) {
        this.depressed = depressed;
    }
}
