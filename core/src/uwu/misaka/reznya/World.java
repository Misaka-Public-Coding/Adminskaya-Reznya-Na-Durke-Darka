package uwu.misaka.reznya;

import com.badlogic.gdx.utils.Array;
import uwu.misaka.reznya.entities.Player;

public class World {
    public Array<Tile> tiles = new Array<Tile>();
    public World(){
        for(int x=0;x<8;x++){
            for(int y=0;y<6;y++){
                if(((x==1||x==2||x==3||x==6)&&y==0)||((x==3||x==4||x==5||x==6)&&y==1)||((x==0||x==1||x==2||x==3||x==4)&&y==2)||((x==3||x==4||x==5||x==6||x==7)&&y==3)||((x==1||x==2||x==3||x==4)&&y==4)||((x==1||x==4||x==5||x==6)&&y==5)){
                    tiles.add(new Tile(x,y,Floor.dirt));
                }else{
                    Floor floor = Floor.randomFloor();
                    while (floor==Floor.dirt){
                        floor=Floor.randomFloor();
                    }
                    tiles.add(new Tile(x,y,floor));
                }
            }
        }
    }
}
