package uwu.misaka.reznya;

import com.badlogic.gdx.graphics.Texture;
import uwu.misaka.reznya.entities.Player;

import java.util.ArrayList;

public class Tile {
    Floor f;
    public ArrayList<Player> players;
    int tile_x;
    int tile_y;

    public int draw_x(){
        return tile_x*Nyahoi.tileSize;
    }
    public int draw_y(){
        return tile_y*Nyahoi.tileSize;
    }

    public Texture texture(){
        return f.texture;
    }

    public boolean canStay(){
        return f.canGoOn;
    }

    public boolean canShootOn(){
        return !f.isSolid;
    }

    public Tile(int x,int y,Floor f){
        this.tile_x=x;
        this.tile_y=y;
        this.f=f;
    }
}
