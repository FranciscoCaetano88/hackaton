package org.academiadecodigo.hackaton.screens.objects.dropable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

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

    }

    private enum DropableType {
        CAKE("cake.png", "depressed_cake.png"),
        RAINBOW("rainbow.png", "depressed_rainbow.png"),
        UNICORN("unicorn.png", "depressed.unicorn.png");

        Texture happyTexture;
        Texture depressedTexture;

        DropableType(String happyPath, String depressedPath) {
            happyTexture = new Texture(Gdx.files.internal(happyPath));
            depressedTexture = new Texture(Gdx.files.internal(depressedPath));
        }

        Dropable getDropable() {
            Dropable dropable = new Dropable();

            dropable.setHappyDropableImage(happyTexture);
            dropable.setDepressedDropableImage(depressedTexture);

            return dropable;
        }
    }

}
