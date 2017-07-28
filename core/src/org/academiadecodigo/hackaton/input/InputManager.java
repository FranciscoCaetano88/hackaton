package org.academiadecodigo.hackaton.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import org.academiadecodigo.hackaton.screens.GameScreen;

/**
 * Created by codecadet on 28/07/17.
 */

public class InputManager implements InputProcessor {

    private GameScreen game;

    public InputManager(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(keycode)) {
            game.getPlayer().getRectangle().x -= GameScreen.MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.getPlayer().getRectangle().x += GameScreen.MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        // make sure the player stays within the screen bounds
        if (game.getPlayer().getRectangle().x < 0) {
            game.getPlayer().getRectangle().x = 0;
        }

        // make sure the player stays within the screen bounds
        if (game.getPlayer().getRectangle().x > GameScreen.SCREEN_SIZE_X - 64) {
            game.getPlayer().getRectangle().x = GameScreen.SCREEN_SIZE_X - 64;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);

        Vector3 touchPos2 = new Vector3();
        touchPos2.set(Gdx.input.getX(1), Gdx.input.getY(1), 0);

        game.getCamera().unproject(touchPos); //TODO: Check if it's really necessary

        if (touchPos.y < 100) {
            game.getPlayer().getRectangle().x = touchPos.x - 64 / 2;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);

        game.checkMouseClick(touchPos);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
