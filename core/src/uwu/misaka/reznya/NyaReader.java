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
        if(character == 119){
            Nyahoi.players.forEach(p->{
                if(p.isYou&&p.canGoUp()){
                    p.goUp();
                }
            });
        }
        if(character == 115){
            Nyahoi.players.forEach(p->{
                if(p.isYou&&p.canGoDown()){
                    p.goDown();
                }
            });
        }
        if(character == 97){
            Nyahoi.players.forEach(p->{
                if(p.isYou&&p.canGoLeft()){
                    p.goLeft();
                }
            });
        }
        if(character == 100){
            System.out.println("TYPED D");
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
