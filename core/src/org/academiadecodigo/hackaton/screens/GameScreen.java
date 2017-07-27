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
import org.academiadecodigo.hackaton.screens.objects.Dropable;
import org.academiadecodigo.hackaton.screens.objects.Player;


public class GameScreen implements Screen {

    private final GameEngine game;

    private int dropsGathered;

    private Score score;

    private Sound dropSound;
    private Music music;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Array<Dropable> raindrops;
    private long lastDropTime;

    private Texture backGroundImage;
    private Rectangle backGround;

    private Player player;
    private Dropable dropable;

    public final static int SCREEN_SIZE_X = 600;
    public final static int SCREEN_SIZE_Y = 480;
    private final int MOVE_SPEED = 500;

    public GameScreen(GameEngine game) {
        this.game = game;
        dropsGathered = 0;
        init();
    }

    private void init() {

        // load the images for the droplet and the player, 64x64 pixels each
        backGroundImage = new Texture(Gdx.files.internal("sad_kitten.jpeg"));

        // load the dropable sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("dropable.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("The Rolling Stones - Paint it black.mp3"));

        score = new Score();

        dropable = new Dropable();
        player = new Player();
    }

    @Override
    public void show() {

        player.create();
        dropable.create();

        // start the playback of the background music immediately
        music.setLooping(true);
        music.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_SIZE_X, SCREEN_SIZE_Y);
        batch = game.getBatch();

        // create a Rectangle to logically represent the player
        backGround = new Rectangle();
        backGround.x = 0; //
        backGround.y = 0; //
        backGround.width = SCREEN_SIZE_X;
        backGround.height = SCREEN_SIZE_Y;

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Dropable>();
        spawnRaindrop();

    }

    private void spawnRaindrop() {

        Dropable raindrop = new Dropable();
        raindrop.create();
        raindrop.getRectangle().x = MathUtils.random(0, SCREEN_SIZE_X - 64);
        raindrop.getRectangle().y = SCREEN_SIZE_Y;
        raindrop.getRectangle().width = 64;
        raindrop.getRectangle().height = 64;
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

        //batch.begin();

        batch.begin();
        batch.draw(backGroundImage, backGround.x, backGround.y);
        game.getFont().draw(game.getBatch(), "Drops Collected: " + dropsGathered, 0, 480);

        batch.end();

        batch.begin();
        //batch.draw(backGroundImage, backGround.x, backGround.y);

        for (Dropable raindrop : raindrops) {
            batch.draw(dropable.getImage(),
                    raindrop.getRectangle().x,
                    raindrop.getRectangle().y);

        }

        score.updateScoreBar();

        batch.end();

        // begin a new batch and draw the player and
        // all drops
        batch.begin();
        batch.draw(player.getBucketImage(), player.getRectangle().x, player.getRectangle().y);

        for (Dropable raindrop : raindrops) {

            batch.draw(dropable.getImage(), raindrop.getRectangle().x, raindrop.getRectangle().y);
        }

        batch.end();


        // process user input
        if (Gdx.input.isTouched()) {

            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.getRectangle().x = touchPos.x - 64 / 2;
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            player.getRectangle().x -= MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            player.getRectangle().x += MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        // make sure the player stays within the screen bounds
        if (player.getRectangle().x < 0) {
            player.getRectangle().x = 0;
        }

        if (player.getRectangle().x > SCREEN_SIZE_X - 64) {
            player.getRectangle().x = SCREEN_SIZE_X - 64;
        }

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {

            spawnRaindrop();
        }

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the player. In the later case we play back
        // a sound effect as well.
        Iterator<Dropable> iter = raindrops.iterator();
        while (iter.hasNext()) {

            Dropable raindrop = iter.next();
            raindrop.getRectangle().y -= MOVE_SPEED * Gdx.graphics.getDeltaTime();

            if (raindrop.getRectangle().y + 64 < 0) {
                iter.remove();

                score.decrementScore();
            }

            if (raindrop.getRectangle().overlaps(player.getRectangle())) {
                dropSound.play();
                iter.remove();

                score.incrementScore();
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
        dropable.getImage().dispose();
        player.getBucketImage().dispose();
        dropSound.dispose();
        music.dispose();
        batch.dispose();

        backGroundImage.dispose();
    }
}