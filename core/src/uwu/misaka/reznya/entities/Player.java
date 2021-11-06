package uwu.misaka.reznya.entities;

import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.Reznya;
import uwu.misaka.reznya.World;

public class Player {
    public boolean isYou = false;

    public int x;
    public int y;

    public int map_x;
    public int map_y;

    public float angle;
    public float target_angle;

    public String name;

    public boolean canGoDown(){
        if(y==0)return false;
        return Reznya.world.getTile(x,y-1).canStay();
    }
    public boolean canGoUp(){
        if(y==Reznya.world.world_y_size-1)return false;
        return Reznya.world.getTile(x,y+1).canStay();
    }
    public boolean canGoLeft(){
        if(x==0)return false;
        return Reznya.world.getTile(x-1,y).canStay();
    }
    public boolean canGoRight(){
        if(x==Reznya.world.world_x_size-1)return false;
        return Reznya.world.getTile(x+1,y).canStay();
    }

    public void goUp(){
        if(canGoUp()){
            y=y+1;
            if(angle==270){
                angle=-90;
            }
            target_angle=0;
            Nyahoi.movementPlayers.add(this);
        }
    }
    public void goDown(){
        if(canGoDown()){
            y=y-1;
            target_angle=180;
            Nyahoi.movementPlayers.add(this);
        }
    }
    public void goLeft(){
        if(canGoLeft()){
            target_angle=90;
            x=x-1;
            Nyahoi.movementPlayers.add(this);
        }
    }
    public void goRight(){
        if(canGoRight()){
            x=x+1;
            if(angle==0){
                angle=360;
            }
            target_angle=270;
            Nyahoi.movementPlayers.add(this);
        }
    }

    public Player(int x,int y, String name){
        this.x = x;
        this.map_x = x* Nyahoi.tileSize;
        this.y = y;
        this.map_y = y * Nyahoi.tileSize;

        this.name = name;

        angle=0;
        target_angle=0;

        Nyahoi.players.add(this);
    }

    public static Player player(int x,int y, String name){
        Player rtn = new Player(x,y,name);
        rtn.isYou=true;
        return rtn;
    }
}
