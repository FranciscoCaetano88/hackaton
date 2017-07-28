package org.academiadecodigo.hackaton.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;
import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_Y;

/**
 * Created by codecadet on 28/07/17.
 */

public class Fog {

    private Texture texture;
    private SpriteBatch fogBatch;
    private Rectangle rectangle;

    public Fog() {
        fogBatch = new SpriteBatch();
        rectangle = new Rectangle(-610,400,10,10);

        this.texture = new Texture(Gdx.files.internal("fog.png"));
    }

    public Texture getTexture() {
        return texture;
    }

    public void draw() {

        fogBatch.begin();
        fogBatch.draw(texture, SCREEN_SIZE_X / 2, SCREEN_SIZE_Y / 2);
        fogBatch.end();

    }

    public void move() {

        // make sure the player stays within the screen bounds
        if (rectangle.x < SCREEN_SIZE_X + 610) {
            rectangle.x += 100 * Gdx.graphics.getDeltaTime();
        }

        // make sure the player stays within the screen bounds
        rectangle.x = -610;

    }
}
