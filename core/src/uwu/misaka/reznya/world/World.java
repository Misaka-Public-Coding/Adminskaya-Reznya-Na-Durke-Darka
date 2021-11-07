package uwu.misaka.reznya.world;

import com.badlogic.gdx.utils.Array;

public class World {
    public Array<Tile> tiles = new Array<Tile>();
    public int world_x_size = 8;
    public int world_y_size = 6;

    public World(){
        for(int x=0;x<world_x_size;x++){
            for(int y=0;y<world_y_size;y++){
                if(((x==1||x==2||x==3||x==6)&&y==0)||((x==3||x==4||x==5||x==6)&&y==1)||((x==0||x==1||x==2||x==3||x==4)&&y==2)||((x==3||x==4||x==5||x==6||x==7)&&y==3)||((x==1||x==2||x==3||x==4)&&y==4)||((x==1||x==4||x==5||x==6)&&y==5)){
                    tiles.add(new Tile(x, y, Floor.dirt));
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
    public Tile getTile(int x, int y){
        if(x<0||y<0||x>=world_x_size||y>=world_y_size){
            throw new IllegalArgumentException("Out of bounds");
        }else{
            for(Tile e:tiles){
                if(e.tile_x==x&&e.tile_y==y){
                    return e;
                }
            }
            return null;
        }
    }
}
