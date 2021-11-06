package uwu.misaka.reznya.entities;

import com.badlogic.gdx.math.Vector2;
import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.Reznya;

public class Bullet {
    public static int maxRange = 4 * Nyahoi.tileSize;

    Player author;
    float angle;
    int target_x;
    int target_y;
    int x;
    int y;

    public Bullet(Player author,int tile_x,int tile_y,int click_x,int click_y){
        this.author=author;
        int x_d = click_x-(tile_x*Nyahoi.tileSize+Nyahoi.tileSize/2);
        int y_d = (Reznya.last_y_size-click_y)-(tile_y*Nyahoi.tileSize+Nyahoi.tileSize/2);
        if(x_d==0||y_d==0){
        if(x_d==0){
            if(y_d>0){
                angle=0;
            }else{
                angle=180;
            }
        }
        if(y_d==0){
            if(x_d>0){
                angle = 270;
            }else{
                angle = 90;
            }
        }
        }else{
            angle= (float) Math.toDegrees(Math.atan(y_d*1f/x_d));
            angle = angle-90;
            if(x_d<0){
                angle-=180;
            }
        }
        if(angle<0){
            angle=360+angle;
        }
        author.target_angle=angle;

        angle = angle+90;

        if(angle>360){
            angle = angle-360;
        }
        this.angle=angle;

        System.out.println(angle+"||"+ author.target_angle);
        Nyahoi.movementPlayers.add(author);
    }
}
