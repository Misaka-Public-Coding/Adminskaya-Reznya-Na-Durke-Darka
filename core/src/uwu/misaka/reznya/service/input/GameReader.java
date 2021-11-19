package uwu.misaka.reznya.service.input;

import com.badlogic.gdx.InputProcessor;
import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.entities.Bullet;

public class GameReader implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if(Nyahoi.canMovement){
        if(character == 119||character==1094){
            Nyahoi.players.forEach(p->{
                if (p.isYou && p.canGoUp() && !p.moved) {
                    p.goUp();
                }
            });
        }
        if(character == 115||character==1099){
            Nyahoi.players.forEach(p->{
                if (p.isYou && p.canGoDown() && !p.moved) {
                    p.goDown();
                }
            });
        }
        if(character == 97||character==1092){
            Nyahoi.players.forEach(p->{
                if (p.isYou && p.canGoLeft() && !p.moved) {
                    p.goLeft();
                }
            });
        }
            if (character == 100 || character == 1074) {
                Nyahoi.players.forEach(p -> {
                    if (p.isYou && p.canGoRight() && !p.moved) {
                        p.goRight();
                    }
                });
            }
            if (character == 32) {
                Nyahoi.players.forEach(p -> {
                    if (p.isYou) {
                        p.moved = true;
                        p.shouted = true;
                    }
                });
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Nyahoi.canMovement){
            Nyahoi.players.forEach(p->{
                if (p.isYou && !p.shouted) {
                    new Bullet(p, p.x, p.y, screenX, screenY);
                }
            });
        }
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
