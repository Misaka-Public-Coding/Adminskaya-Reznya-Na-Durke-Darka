package uwu.misaka.reznya.service.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import uwu.misaka.reznya.Nyahoi;

public class GameEndMenuReader implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            Nyahoi.loadMainMenu();
        }
        return false;
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
        Nyahoi.loadMainMenu();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
