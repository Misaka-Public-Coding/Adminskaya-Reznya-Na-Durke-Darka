package uwu.misaka.reznya;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import uwu.misaka.reznya.entities.Player;

public class Nyahoi {
    public static Array<Player> players;

    public static final int tileSize=80;

    public static void drawWithOffset(SpriteBatch b, Texture t,int x,int y){
        int leftOffset =(int)((Reznya.last_x_size-640)/4*Reznya.camera.zoom);
        int final_x= leftOffset+x;
        b.draw(t,final_x,y,tileSize,tileSize);
    }
}
