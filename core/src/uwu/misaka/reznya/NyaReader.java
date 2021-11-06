package uwu.misaka.reznya;

import com.badlogic.gdx.InputProcessor;

public class NyaReader implements InputProcessor {
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
                if(p.isYou&&p.canGoUp()){
                    p.goUp();
                }
            });
        }
        if(character == 115||character==1099){
            Nyahoi.players.forEach(p->{
                if(p.isYou&&p.canGoDown()){
                    p.goDown();
                }
            });
        }
        if(character == 97||character==1092){
            Nyahoi.players.forEach(p->{
                if(p.isYou&&p.canGoLeft()){
                    p.goLeft();
                }
            });
        }
        if(character == 100||character==1074){
            Nyahoi.players.forEach(p->{
                if(p.isYou&&p.canGoRight()){
                    p.goRight();
                }
            });
        }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
