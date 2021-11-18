package uwu.misaka.reznya.entities;

import com.badlogic.gdx.utils.Array;
import uwu.misaka.reznya.Nyahoi;
import uwu.misaka.reznya.Reznya;
import uwu.misaka.reznya.world.Tile;

import static java.lang.Math.sqrt;
import static uwu.misaka.reznya.Nyahoi.*;

public class Bullet {
    public static int maxRange = 3 * tileSize;

    public Player author;
    float angle;
    public float facing_angle;
    int target_x;
    int target_y;
    public int x;
    public int y;

    public Bullet(Player author,int tile_x,int tile_y,int click_x,int click_y){
        this.author=author;
        x = tile_x* tileSize + tileSize/2;
        y = tile_y* tileSize + tileSize/2;
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
            if(x_d<0){
                angle-=180;
            }
            angle-=90;
        }
        if(angle<0){
            angle=360+angle;
        }

        target_x=x;
        target_y=y;

        if(angle>22.5&&angle<67.5){
            angle=45;
            target_x = (int) (x-(maxRange*sqrt(2)/2));
            target_y = (int) (y+(maxRange*sqrt(2)/2));
        }
        if(angle>67.5&&angle<112.5){
            angle=90;
            target_x= x-maxRange;
        }
        if(angle>112.5&&angle<157.5){
            angle=135;
            target_x = (int) (x-(maxRange*sqrt(2)/2));
            target_y = (int) (y-(maxRange*sqrt(2)/2));
        }
        if(angle>157.5&&angle<202.5){
            angle=180;
            target_y = y-maxRange;
        }
        if(angle>202.5&&angle<247.5){
            angle=225;
            target_x = (int) (x+(maxRange*sqrt(2)/2));
            target_y = (int) (y-(maxRange*sqrt(2)/2));
        }
        if(angle>247.5&&angle<292.5){
            angle=270;
            target_x = x+maxRange;
        }
        if(angle>292.5&&angle<337.5){
            angle=315;
            target_x = (int) (x+(maxRange*sqrt(2)/2));
            target_y = (int) (y+(maxRange*sqrt(2)/2));
        }
        if(angle>337.5||angle<22.5){
            angle=0;
            target_y = y+maxRange;
        }

        author.target_angle=angle;
        facing_angle=angle;

        angle = angle + 90;

        if (angle > 360) {
            angle = angle - 360;
        }

        Nyahoi.movementPlayers.add(author);
        Nyahoi.movementBullets.add(this);
        author.shouted = true;
    }

    public Bullet(Player author, Player target) {
        this.author = author;
        x = author.x * tileSize + tileSize / 2;
        y = author.y * tileSize + tileSize / 2;
        double x_d = target.x - author.x;
        double y_d = target.y - author.y;
        if (x_d == 0 || y_d == 0) {
            if (x_d == 0) {
                if (y_d > 0) {
                    angle = 0;
                } else {
                    angle = 180;
                }
            }
            if (y_d == 0) {
                if (x_d > 0) {
                    angle = 270;
                } else {
                    angle = 90;
                }
            }
        } else {
            angle = (float) Math.toDegrees(Math.atan(y_d * 1f / x_d));
            if (x_d < 0) {
                angle -= 180;
            }
            angle -= 90;
        }
        if (angle < 0) {
            angle = 360 + angle;
        }

        target_x = x;
        target_y = y;

        if (angle > 22.5 && angle < 67.5) {
            angle = 45;
            target_x = (int) (x - (maxRange * sqrt(2) / 2));
            target_y = (int) (y + (maxRange * sqrt(2) / 2));
        }
        if (angle > 67.5 && angle < 112.5) {
            angle = 90;
            target_x = x - maxRange;
        }
        if (angle > 112.5 && angle < 157.5) {
            angle = 135;
            target_x = (int) (x - (maxRange * sqrt(2) / 2));
            target_y = (int) (y - (maxRange * sqrt(2) / 2));
        }
        if (angle > 157.5 && angle < 202.5) {
            angle = 180;
            target_y = y - maxRange;
        }
        if (angle > 202.5 && angle < 247.5) {
            angle = 225;
            target_x = (int) (x + (maxRange * sqrt(2) / 2));
            target_y = (int) (y - (maxRange * sqrt(2) / 2));
        }
        if (angle > 247.5 && angle < 292.5) {
            angle = 270;
            target_x = x + maxRange;
        }
        if (angle > 292.5 && angle < 337.5) {
            angle = 315;
            target_x = (int) (x + (maxRange * sqrt(2) / 2));
            target_y = (int) (y + (maxRange * sqrt(2) / 2));
        }
        if (angle > 337.5 || angle < 22.5) {
            angle = 0;
            target_y = y + maxRange;
        }

        author.target_angle = angle;
        facing_angle = angle;

        angle = angle + 90;

        if (angle > 360) {
            angle = angle - 360;
        }

        Nyahoi.movementPlayers.add(author);
        Nyahoi.movementBullets.add(this);
        author.shouted = true;
    }

    public void collide() {
        Array<Player> toRemove = new Array<>();
        for (Player p : players) {
            if (p != author && this.x < p.map_x + tileSize && this.x > p.map_x && this.y > p.map_y && this.y < p.map_y + tileSize) {
                toRemove.add(p);
                Nyahoi.bulletsToRm.add(this);
            }
        }
        for (Tile t : Reznya.world.tiles) {
            if(!t.canShootOn()&&t.draw_x()<x&&t.draw_x()+tileSize>x&&t.draw_y()<y&&t.draw_y()+tileSize>y){
                Nyahoi.bulletsToRm.add(this);
            }
        }
        players.removeAll(toRemove,false);
    }
    public void move(){
        if(x!=target_x&&Math.abs(target_x-x)>5){
            if(target_x>x){
                x+=5;
            }else{
                x-=5;
            }
        }else{
            x=target_x;
        }
        if(y!=target_y&&Math.abs(target_y-y)>5){
            if(target_y>y){
                y+=5;
            }else{
                y-=5;
            }
        }else{
            y=target_y;
        }
        if(x==target_x&&y==target_y){
            bulletsToRm.add(this);
        }
    }
}
