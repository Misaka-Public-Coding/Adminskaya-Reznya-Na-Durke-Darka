package uwu.misaka.reznya;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import uwu.misaka.reznya.entities.Bullet;
import uwu.misaka.reznya.entities.Player;

public class Nyahoi {
    public static Array<Player> players = new Array<>();

    public static Array<Player> movementPlayers = new Array<>();
    public static Array<Bullet> movementBullets = new Array<>();
    public static Array<Bullet> bulletsToRm = new Array<>();
    public static boolean canMovement = true;

    public static BitmapFont font = new BitmapFont();

    public static void updateMovement(){
        if(movementPlayers.size!=0){
            canMovement = false;
            Array<Player> movementEnd = new Array<>();
            movementPlayers.forEach(p->{
                if(p.angle!=p.target_angle){
                    if(p.angle>p.target_angle){
                        if(p.angle-p.target_angle>2){
                            p.angle-=2;
                        }else{
                            p.angle=p.target_angle;
                        }
                    }else{
                        if(p.target_angle-p.angle>2){
                            p.angle+=2;
                        }else{
                            p.angle=p.target_angle;
                        }
                    }
                    return;
                }
                if(p.x*Nyahoi.tileSize > p.map_x){
                    p.map_x++;
                }
                if(p.x*Nyahoi.tileSize < p.map_x){
                    p.map_x--;
                }
                if(p.y*Nyahoi.tileSize > p.map_y){
                    p.map_y++;
                }
                if(p.y*Nyahoi.tileSize < p.map_y){
                    p.map_y--;
                }
                if(p.x*Nyahoi.tileSize == p.map_x&&p.y*Nyahoi.tileSize == p.map_y&&p.angle==p.target_angle){
                    movementEnd.add(p);
                }
            });
            movementEnd.forEach(p->movementPlayers.removeValue(p,true));
        }
        if(movementPlayers.size==0){
            if(movementBullets.size==0){
                canMovement = true;
            }else {
                canMovement = false;
                movementBullets.forEach(b -> {
                    b.collide();
                    b.move();
                });
                movementBullets.removeAll(bulletsToRm, false);
            }
        }
    }

    public static final int tileSize=80;

    public static void drawWithOffset(SpriteBatch b, Texture t,int x,int y){
        int leftOffset =(int)((Reznya.last_x_size-640)/4*Reznya.camera.zoom);
        int final_x= leftOffset+x;
        b.draw(t,final_x,y,tileSize,tileSize);
    }
    public static void drawPlayer(SpriteBatch b, Player p){
        int leftOffset =(int)((Reznya.last_x_size-640)/4*Reznya.camera.zoom);
        int final_x= leftOffset+p.map_x;
        TextureRegion rg = new TextureRegion(ContentLoader.player_example);
        b.draw(rg,final_x,p.map_y,Nyahoi.tileSize/2f,Nyahoi.tileSize/2f,Nyahoi.tileSize,Nyahoi.tileSize,1,1,p.angle);
        font.draw(b,p.name,final_x, p.map_y+(9*Nyahoi.tileSize/10f),Nyahoi.tileSize,0,false);
    }
    public static void drawBullet(SpriteBatch b, Bullet bullet){
        int leftOffset =(int)((Reznya.last_x_size-640)/4*Reznya.camera.zoom);
        int final_x= leftOffset+bullet.x-(tileSize/2);
        TextureRegion rg = new TextureRegion(ContentLoader.bullet_example);
        b.draw(rg,final_x,bullet.y-(tileSize/2f),Nyahoi.tileSize/2f,Nyahoi.tileSize/2f,Nyahoi.tileSize,Nyahoi.tileSize,1,1,bullet.facing_angle);
    }
}
