package org.academiadecodigo.hackaton.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import org.academiadecodigo.hackaton.GameEngine;
import org.academiadecodigo.hackaton.screens.objects.Bucket;
import org.academiadecodigo.hackaton.screens.objects.Drop;


public class GameScreen implements Screen {

    final GameEngine game;

    private int dropsGathered;


    private Sound dropSound;
    private Music rainMusic;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Array<Drop> raindrops;
    private long lastDropTime;

    private Texture backGroundImage;
    private Rectangle backGround;

    private Bucket bucket;
    private Drop drop;

    public final static int SCREEN_SIZE_X = 600;
    public final static int SCREEN_SIZE_Y = 480;
    private final int MOVE_SPEED = 500;

    public GameScreen(GameEngine game) {
        this.game = game;
        dropsGathered = 0;
        init();
    }

    private void init() {

        // load the images for the droplet and the bucket, 64x64 pixels each
        backGroundImage = new Texture(Gdx.files.internal("background.jpg"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        drop = new Drop();
        bucket = new Bucket();
    }

    @Override
    public void show() {

        bucket.create();
        drop.create();

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_SIZE_X, SCREEN_SIZE_Y);
        batch = new SpriteBatch();

        // create a Rectangle to logically represent the bucket
        backGround = new Rectangle();
        backGround.x = 0; // center the bucket horizontally
        backGround.y = 0; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        backGround.width = SCREEN_SIZE_X;
        backGround.height = SCREEN_SIZE_Y;


        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Drop>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {

        Drop raindrop = new Drop();
        raindrop.create();
        raindrop.getRectagle().x = MathUtils.random(0, SCREEN_SIZE_X - 64);
        raindrop.getRectagle().y = SCREEN_SIZE_Y;
        raindrop.getRectagle().width = 64;
        raindrop.getRectagle().height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {

        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        game.getFont().draw(game.getBatch(), "Drops Collected: " + dropsGathered, 0, 480);

        batch.draw(backGroundImage, backGround.x, backGround.y);

        for (Drop raindrop : raindrops) {
            batch.draw(drop.getDropImage(),
                    raindrop.getRectagle().x,
                    raindrop.getRectagle().y);

        }

        batch.end();

        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();
        batch.draw(bucket.getBucketImage(), bucket.getRectangle().x, bucket.getRectangle().y);

        for (Drop raindrop : raindrops) {

            batch.draw(drop.getDropImage(), raindrop.getRectagle().x, raindrop.getRectagle().y);
        }

        batch.end();


        // process user input
        if (Gdx.input.isTouched()) {

            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.getRectangle().x = touchPos.x - 64 / 2;
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            bucket.getRectangle().x -= MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            bucket.getRectangle().x += MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        // make sure the bucket stays within the screen bounds
        if (bucket.getRectangle().x < 0) {
            bucket.getRectangle().x = 0;
        }

        if (bucket.getRectangle().x > SCREEN_SIZE_X - 64) {
            bucket.getRectangle().x = SCREEN_SIZE_X - 64;
        }

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {

            spawnRaindrop();
        }

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
        Iterator<Drop> iter = raindrops.iterator();
        while (iter.hasNext()) {

            Drop raindrop = iter.next();
            raindrop.getRectagle().y -= MOVE_SPEED * Gdx.graphics.getDeltaTime();

            if (raindrop.getRectagle().y + 64 < 0) {
                iter.remove();
            }

            if (raindrop.getRectagle().overlaps(bucket.getRectangle())) {
                dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        drop.getDropImage().dispose();
        bucket.getBucketImage().dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();

        backGroundImage.dispose();
    }
}