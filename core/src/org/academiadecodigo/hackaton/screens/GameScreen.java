package org.academiadecodigo.hackaton.screens;

import java.util.Iterator;

import com.badlogic.gdx.Application;
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

import org.academiadecodigo.hackaton.objects.Fog;
import org.academiadecodigo.hackaton.objects.dropable.Dropable;
import org.academiadecodigo.hackaton.objects.dropable.DropableFactory;
import org.academiadecodigo.hackaton.objects.text.Message;
import org.academiadecodigo.hackaton.objects.text.MessageFactory;
import org.academiadecodigo.hackaton.objects.text.Score;
import org.academiadecodigo.hackaton.objects.Player;

public class GameScreen implements Screen {

    public final static int SCREEN_SIZE_X = GameEngine.WIDTH;
    public final static int SCREEN_SIZE_Y = GameEngine.HEIGHT;

    public static final int MOVE_SPEED = 400;
    private final int DROP_SPEED = 200;

    private final double PRODUCTION_RATE = 2;// dropables per second

    private final GameEngine gameEngine;

    private Score score;

    private Sound happyCatchSound;
    private Music music;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Array<Dropable> dropables;
    private long lastDropTime;
    private long lastMessageTime = TimeUtils.millis();

    private Texture backGroundImage;

    private Player player;

    private Fog fog;
    private Music happyEndMusic;


    private Message message = MessageFactory.generateMessage();
    private Sound sadCatchSound;

    public GameScreen(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        init();
    }

    private void init() {


        // load the images for the droplet and the player, 64x64 pixels each
        backGroundImage = new Texture(Gdx.files.internal("game_background.jpg"));

        // load the dropable sound effect and the rain background "music"
        happyCatchSound = Gdx.audio.newSound(Gdx.files.internal("yay.wav"));
        sadCatchSound = Gdx.audio.newSound(Gdx.files.internal("sigh.mp3"));

        music = Gdx.audio.newMusic(Gdx.files.internal("The Rolling Stones - Paint it black.mp3"));

        happyEndMusic = Gdx.audio.newMusic(Gdx.files.internal("happy_end.wav"));

        score = new Score();

        fog = new Fog(0);

        player = new Player();

        //Gdx.input.setInputProcessor(new InputManager(this));
    }

    @Override
    public void show() {

        // start the playback of the background music immediately
        music.setLooping(true);
        music.setVolume(0.45f);
        music.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_SIZE_X, SCREEN_SIZE_Y);
        batch = gameEngine.getBatch();

        /*
        // create a Rectangle to logically represent the player
        backGround = new Rectangle();
        backGround.x = 0;
        backGround.y = 0;
        backGround.width = SCREEN_SIZE_X;
        backGround.height = SCREEN_SIZE_Y;
        */

        // create the dropables array and spawn the first raindrop
        dropables = new Array<Dropable>();

        spawnDropable();
    }

    private void spawnDropable() {

        Dropable dropable = DropableFactory.generateDropable();

        dropable.getRectangle().x = MathUtils.random(0, SCREEN_SIZE_X - 64);
        dropable.getRectangle().y = SCREEN_SIZE_Y;
        dropable.getRectangle().width = 64;
        dropable.getRectangle().height = 64;

        dropables.add(dropable);

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

        // tell the camera to handleInput its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        //batch.begin();
        //batch.setColor(0.05f,0.05f,0.05f,0.8f);
        batch.setColor(0.25f, 0.25f, 0.25f, 0.8f);


        batch.begin();
        batch.draw(backGroundImage, 0, 0);
        batch.end();

        batch.setColor(255, 255, 255, 0.5f);


        score.draw();


        batch.setColor(1, 1, 1, 1);
        batch.begin();
        //batch.draw(backGroundImage, backGround.x, backGround.y);

        for (Dropable dropable : dropables) {
            batch.draw(dropable.getImage(),

                    dropable.getRectangle().x,
                    dropable.getRectangle().y);
        }

        batch.end();

        score.updateScoreBar();

        // begin a new batch and draw the player and
        // all drops
        batch.begin();
        batch.draw(player.getImage(), player.getRectangle().x, player.getRectangle().y);

        for (Dropable dropable : dropables) {

            batch.draw(dropable.getImage(), dropable.getRectangle().x, dropable.getRectangle().y);

        }

        message.draw(batch, gameEngine.getFont(), delta, 20, 760);

        batch.end();


        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {

            fog.draw();
        }


        handleInput();

        checkScore();
    }

    private void checkScore() {

        //System.out.println("Score: " + score.getScore());
        EndScreen endScreen = new EndScreen(gameEngine);

        if (score.getScore() <= 0) {

            endScreen.setBackGroundImage(new Texture(Gdx.files.internal("happy_end.png")));
            endScreen.setMusic(happyEndMusic);

            dispose();
            gameEngine.setScreen(endScreen);
            return;
        }

        if (score.getScore() >= 240) {

            endScreen.setBackGroundImage(new Texture(Gdx.files.internal("sad_end.png")));
            endScreen.setMusic(music);

            dispose();
            gameEngine.setScreen(endScreen);

        }

    }


    public void handleInput() {
        // process user input
        if (Gdx.input.isTouched()) {

            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);


            Vector3 touchPos2 = new Vector3();
            touchPos2.set(Gdx.input.getX(1), Gdx.input.getY(1), 0);

            camera.unproject(touchPos);

            if (touchPos.y < 100) {
                player.getRectangle().x = touchPos.x - 64 / 2;
            }

            checkMouseClick(touchPos);

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

        // make sure the player stays within the screen bounds
        if (player.getRectangle().x > SCREEN_SIZE_X - 64) {
            player.getRectangle().x = SCREEN_SIZE_X - 64;
        }
        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > (1000000000 / PRODUCTION_RATE)) {
            player.changeImage();
            spawnDropable();
        }

        if (TimeUtils.millis() - lastMessageTime > 5000) {
            message = MessageFactory.generateMessage();
            lastMessageTime = TimeUtils.millis();
        }

        // move the dropables, remove any that are beneath the bottom edge of
        // the screen or that hit the player. In the later case we play back
        // a sound effect as well.
        Iterator<Dropable> iter = dropables.iterator();
        while (iter.hasNext()) {

            Dropable dropable = iter.next();
            dropable.getRectangle().y -= DROP_SPEED * Gdx.graphics.getDeltaTime();

            if (dropable.getRectangle().y + 64 < 0) {

                score.decrementScore();
                iter.remove();
            }

            if (dropable.getRectangle().overlaps(player.getRectangle())) {

                iter.remove();

                if (dropable.isDepressed()) {

                    happyCatchSound.play();
                    score.incrementScore();
                    score.incrementScore();

                } else {

                    sadCatchSound.play();
                    score.decrementScore();
                    score.decrementScore();
                }

            }
        }
    }

    public void checkMouseClick(Vector3 touchPos) {

        float x = touchPos.x;
        float y = touchPos.y;

        Rectangle mouse = new Rectangle();

        mouse.set(x, y, 10, 10);

        for (Dropable dropable : dropables) {

            if (mouse.overlaps(dropable.getRectangle())) {
                dropable.setDepressed(true);
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
        happyCatchSound.dispose();
        music.dispose();
        score.dispose();

        //batch.dispose();
        backGroundImage.dispose();

    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}