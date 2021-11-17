package uwu.misaka.reznya;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import uwu.misaka.reznya.entities.Bullet;
import uwu.misaka.reznya.entities.Player;
import uwu.misaka.reznya.service.ContentLoader;
import uwu.misaka.reznya.service.GameLogic;
import uwu.misaka.reznya.service.input.GameReader;
import uwu.misaka.reznya.service.input.MainMenuReader;
import uwu.misaka.reznya.world.Tile;
import uwu.misaka.reznya.world.World;

import static uwu.misaka.reznya.Reznya.*;

public class Nyahoi {
    public static Array<Player> players = new Array<>();

    public static final int tileSize = 80;

    public static Array<Player> movementPlayers = new Array<>();
    public static Array<Bullet> movementBullets = new Array<>();
    public static Array<Bullet> bulletsToRm = new Array<>();
    public static boolean canMovement = true;

    public static BitmapFont font = new BitmapFont();
    public static GlyphLayout fontSizer = new GlyphLayout();

    public static Runnable renderActions = () -> ScreenUtils.clear(0, 0, 0, 1);

    public static void updateMovement() {
        if (movementBullets.size != 0) {
            canMovement = false;
            movementBullets.forEach(b -> {
                Player p = b.author;
                if (p.angle != b.facing_angle) {
                    if (p.angle > b.facing_angle) {
                        if (p.angle - b.facing_angle > 2) {
                            p.angle -= 2;
                        } else {
                            p.angle = b.facing_angle;
                        }
                    } else {
                        if (b.facing_angle - p.angle > 2) {
                            p.angle += 2;
                        } else {
                            p.angle = b.facing_angle;
                        }
                    }
                    return;
                }
                b.collide();
                b.move();
            });
            movementBullets.removeAll(bulletsToRm, false);
        }
        if (movementPlayers.size != 0 && movementBullets.size == 0) {
            canMovement = false;
            Array<Player> movementEnd = new Array<>();
            movementPlayers.forEach(p -> {
                if (p.angle != p.target_angle) {
                    if (p.angle > p.target_angle) {
                        if (p.angle - p.target_angle > 2) {
                            p.angle -= 2;
                        } else {
                            p.angle = p.target_angle;
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
                if (p.y * Nyahoi.tileSize < p.map_y) {
                    p.map_y--;
                }
                if (p.x * Nyahoi.tileSize == p.map_x && p.y * Nyahoi.tileSize == p.map_y && p.angle == p.target_angle) {
                    movementEnd.add(p);
                }
            });
            movementEnd.forEach(p -> movementPlayers.removeValue(p, true));
        }
        if (movementBullets.size == 0 && movementPlayers.size == 0) {
            canMovement = true;
        }
    }

    public static void drawWithOffset(SpriteBatch b, Texture t,int x,int y){
        int leftOffset =(int)((Reznya.last_x_size-640)/4*Reznya.camera.zoom);
        int final_x= leftOffset+x;
        b.draw(t, final_x, y, tileSize, tileSize);
    }

    public static String playerName = "peshka owlera";

    public static void drawBullet(SpriteBatch b, Bullet bullet) {
        int leftOffset = (int) ((Reznya.last_x_size - 640) / 4 * Reznya.camera.zoom);
        int final_x = leftOffset + bullet.x - (tileSize / 2);
        TextureRegion rg = new TextureRegion(ContentLoader.bullet_example);
        b.draw(rg, final_x, bullet.y - (tileSize / 2f), Nyahoi.tileSize / 2f, Nyahoi.tileSize / 2f, Nyahoi.tileSize, Nyahoi.tileSize, 1, 1, bullet.facing_angle);
    }

    public static Runnable renderMainMenu = () -> {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.draw(ContentLoader.menu_banner, ((((Reznya.last_x_size - 640) / 4f) * Reznya.camera.zoom)), (last_y_size - (ContentLoader.menu_banner.getHeight() / 2f)) * camera.zoom, (ContentLoader.menu_banner.getWidth() / 2f) * camera.zoom, (ContentLoader.menu_banner.getHeight() / 2f) * camera.zoom);
        font.draw(batch, playerName, ((last_x_size - getTextWidth(font, playerName)) / 2f) * camera.zoom, last_y_size / 2f * camera.zoom);
    };

    public static Runnable renderGame = () -> {
        GameLogic.logicTurn();
        Nyahoi.updateMovement();
        for (Tile t : world.tiles) {
            Nyahoi.drawWithOffset(batch, t.texture(), t.draw_x(), t.draw_y());
        }
        for (Player p : Nyahoi.players) {
            Nyahoi.drawPlayer(batch, p);
        }
        for (Bullet b : Nyahoi.movementBullets) {
            Nyahoi.drawBullet(batch, b);
        }
    };

    public static void drawPlayer(SpriteBatch b, Player p) {
        int leftOffset = (int) ((Reznya.last_x_size - 640) / 4 * Reznya.camera.zoom);
        int final_x = leftOffset + p.map_x;
        TextureRegion rg = new TextureRegion(ContentLoader.player_example);
        b.draw(rg, final_x, p.map_y, Nyahoi.tileSize / 2f, Nyahoi.tileSize / 2f, Nyahoi.tileSize, Nyahoi.tileSize, 1, 1, p.angle);
        if (p.isYou) {
            font.setColor(0, 1, 0, 1);
        } else {
            font.setColor(1, 0, 0, 1);
        }
        font.draw(b, p.name, final_x + (tileSize / 2f) - (getTextWidth(font, p.name) / 2f), p.map_y + (9 * Nyahoi.tileSize / 10f));
        font.setColor(1, 1, 1, 1);
    }

    public static int getTextWidth(BitmapFont bf, String t) {
        fontSizer.setText(bf, t);
        return (int) fontSizer.width;
    }

    public static void loadGameRender() {
        world = new World();
        Player.player(1, 0, playerName);
        new Player(0, 2, "ICHI");
        new Player(1, 5, "NI");
        new Player(6, 0, "SAN");
        new Player(6, 5, "NYA");
        new Player(7, 3, "Arigato");
        Gdx.input.setInputProcessor(new GameReader());
        renderActions = renderGame;
        GameLogic.initLogic();
    }

    public static void loadMainMenu() {
        Gdx.input.setInputProcessor(new MainMenuReader());
        renderActions = renderMainMenu;
    }

    public static void makeABotMove(Player p) {
        if (p.isYou || !players.contains(p, false)) {
            return;
        }
        Player target = null;
        for (Player t : players) {
            if (length(t, p) < Bullet.maxRange * Bullet.maxRange && t != p) {
                target = t;
                break;
            }
        }
        if (target == null && !p.moved && canMovement) {
            boolean left = world.canStay(p.x - 1, p.y);
            boolean right = world.canStay(p.x + 1, p.y);
            boolean up = world.canStay(p.x, p.y + 1);
            boolean down = world.canStay(p.x, p.y - 1);
            while (true) {
                int i = (int) (Math.random() * 4);
                if (i == 0 && left) {
                    p.goLeft();
                    break;
                }
                if (i == 1 && right) {
                    p.goRight();
                    break;
                }
                if (i == 2 && up) {
                    p.goUp();
                    break;
                }
                if (i == 3 && down) {
                    p.goDown();
                    break;
                }
                System.out.println(p.name + " " + i + " " + left + " " + right + " " + up + " " + down + " ");
            }
        }
        if (target != null && !p.shouted && canMovement) {
            System.out.println(p.name + "|" + target.name);
            new Bullet(p, target);
        }
        if (!p.moved && canMovement) {
            boolean left = world.canStay(p.x - 1, p.y);
            boolean right = world.canStay(p.x + 1, p.y);
            boolean up = world.canStay(p.x, p.y + 1);
            boolean down = world.canStay(p.x, p.y - 1);
            while (true) {
                int i = (int) (Math.random() * 4);
                if (i == 0 && left) {
                    p.goLeft();
                    break;
                }
                if (i == 1 && right) {
                    p.goRight();
                    break;
                }
                if (i == 2 && up) {
                    p.goUp();
                    break;
                }
                if (i == 3 && down) {
                    p.goDown();
                    break;
                }
                System.out.println(p.name + " " + i + " " + left + " " + right + " " + up + " " + down + " ");
            }
        }
        if (canMovement && !p.shouted) {
            p.shouted = true;
        }
    }

    public static int length(Player a, Player b) {
        int x1 = a.x * tileSize;
        int x2 = b.x * tileSize;
        int y1 = a.x * tileSize;
        int y2 = b.y * tileSize;
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}
