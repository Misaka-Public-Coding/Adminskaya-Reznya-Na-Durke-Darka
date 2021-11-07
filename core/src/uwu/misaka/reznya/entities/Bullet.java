package uwu.misaka.reznya.entities;

import com.badlogic.gdx.graphics.Camera;
import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.Reznya;

import static java.lang.Math.sqrt;
import static uwu.misaka.reznya.Nyahoi.tileSize;

public class Bullet {
    public static int maxRange = 4 * tileSize;

    Player author;
    float angle;
    float facing_angle;
    int target_x;
    int target_y;
    int x;
    int y;

    public Bullet(Player author,int tile_x,int tile_y,int click_x,int click_y){
        this.author=author;
        x=tile_x* tileSize+ tileSize/2;
        y=tile_y* tileSize+ tileSize/2;
        double x_d = click_x-(((tile_x* tileSize+ tileSize/2f)/Reznya.camera.zoom+(Reznya.last_x_size-640)/4f));
        double y_d = (Reznya.last_y_size-click_y)-((tile_y* tileSize+ tileSize/2f)/Reznya.camera.zoom);
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
        facing_angle=angle;

        angle = angle+90;

        if(angle>360){
            angle = angle-360;
        }

        System.out.println(angle+"||"+ author.target_angle);
        Nyahoi.movementPlayers.add(author);
    }
}
