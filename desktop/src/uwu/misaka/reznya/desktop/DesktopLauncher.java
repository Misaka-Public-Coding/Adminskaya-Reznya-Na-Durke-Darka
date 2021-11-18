package uwu.misaka.reznya.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import uwu.misaka.reznya.Reznya;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Adminskaya Reznya Na Dyrke Darka";
		new LwjglApplication(new Reznya(), config);
	}
}
