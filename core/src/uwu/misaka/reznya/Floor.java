package uwu.misaka.reznya;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Collections;

public class Floor {
    boolean isSolid;
    boolean canGoOn;
    Texture texture;

    public static Array<Floor> floors = new Array<>();

    public static Floor randomFloor(){
        floors.shuffle();
        return floors.get((int) (Math.random() / floors.size));
    }

    public static Floor wall = wall();
    public static Floor water = water();
    public static Floor dirt = dirt();

    public Floor(){
        floors.add(this);
    }

    public static Floor wall(){
        Floor rtn = new Floor();

        rtn.isSolid=true;
        rtn.canGoOn=false;
        rtn.texture = ContentLoader.floor_wall;

        return rtn;
    }
    public static Floor dirt(){
        Floor rtn = new Floor();

        rtn.isSolid=false;
        rtn.canGoOn=true;
        rtn.texture = ContentLoader.floor_dirt;

        return rtn;
    }
    public static Floor water(){
        Floor rtn = new Floor();

        rtn.isSolid=false;
        rtn.canGoOn=false;
        rtn.texture = ContentLoader.floor_water;

        return rtn;
    }
}
