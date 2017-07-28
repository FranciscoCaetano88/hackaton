package org.academiadecodigo.hackaton.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import org.academiadecodigo.hackaton.screens.GameScreen;

import static com.badlogic.gdx.Gdx.input;

public class InputManager implements InputProcessor {

    private GameScreen game;

    public InputManager(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);

        game.getCamera().unproject(touchPos); //TODO: Check if it's really necessary

        if (touchPos.y < 100) {
            game.getPlayer().getRectangle().x = touchPos.x - 64 / 2;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(input.getX(pointer), input.getY(pointer), 0);

        game.getCamera().unproject(touchPos);

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
