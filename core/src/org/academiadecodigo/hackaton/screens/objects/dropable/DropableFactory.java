package org.academiadecodigo.hackaton.screens.objects.dropable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class DropableFactory {

    public static Dropable generateDropable() {


        int randomInt = MathUtils.random(0, 10);

        if (randomInt <= 5) {
            return DropableType.values()[0].getDropable();
        }

        if (randomInt <= 8) {
            return DropableType.values()[1].getDropable();
        }

        return DropableType.values()[2].getDropable();


        //return DropableType.values()[MathUtils.random(0,DropableType.values().length-1)].getDropable();
    }

    private static enum DropableType {
        CAKE("cake.png", "depressed_cake.png"),
        RAINBOW("rainbow.png", "depressed_rainbow.png"),
        UNICORN("unicorn.png", "depressed.unicorn.png");

        Texture happyTexture;
        Texture depressedTexture;

        DropableType(String happyPath, String depressedPath) {
            happyTexture = new Texture(Gdx.files.internal(happyPath));
            depressedTexture = new Texture(Gdx.files.internal(happyPath));
        }

        Dropable getDropable() {
            Dropable dropable = new Dropable();
            dropable.setRectangle(new Rectangle());
            dropable.setHappyDropableImage(happyTexture);
            dropable.setDepressedDropableImage(depressedTexture);

            return dropable;
        }
    }

}
