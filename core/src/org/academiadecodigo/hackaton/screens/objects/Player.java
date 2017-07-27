package org.academiadecodigo.hackaton.screens.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import static org.academiadecodigo.hackaton.screens.GameScreen.SCREEN_SIZE_X;


/**
 * Created by codecadet on 27/07/2017.
 */

public class Player {

    private Texture bucketImage;
    private Rectangle bucket;


    public void create(){

        bucketImage = new Texture(Gdx.files.internal("player.png"));


        // create a Rectangle to logically represent the bucket
        bucket=new Rectangle();
        //bucket.setX()
        bucket.x = SCREEN_SIZE_X / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        bucket.width = 64;
        bucket.height = 64;
    }
    public Texture getBucketImage() {
        return bucketImage;
    }

    public void setBucketImage(Texture bucketImage) {
        this.bucketImage = bucketImage;
    }

    public Rectangle getRectangle() {
        return bucket;
    }

    public void setBucket(Rectangle bucket) {
        this.bucket = bucket;
    }
}
