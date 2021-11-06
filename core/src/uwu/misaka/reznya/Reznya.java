package uwu.misaka.reznya;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import uwu.misaka.reznya.entities.Player;

public class Reznya extends ApplicationAdapter{
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static World world;

	public static int last_x_size = 0;
	public static int last_y_size = 0;
	
	@Override
	public void create () {
		Player.player(1,0,"hentaichan");
		world=new World();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,400);
		Gdx.input.setInputProcessor(new NyaReader());
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0, 1);
		Nyahoi.updateMovement();
		batch.begin();
		for(Tile t:world.tiles){
			Nyahoi.drawWithOffset(batch,t.texture(),t.draw_x(),t.draw_y());
		}
		for(Player p : Nyahoi.players){
			Nyahoi.drawPlayer(batch,p);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//todo dispose elements
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camera.zoom=(1/(1f/480f*height));
		last_x_size=width;
		last_y_size=height;
		camera.setToOrtho(false,width,height);
		camera.update();
	}
}
