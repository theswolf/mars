package core.september.marstravel.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import core.september.marstravel.MarsGame;
import core.september.marstravel.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constants.GAME_HEIGHT;
		config.width = Constants.GAME_WIDTH;
		new LwjglApplication(new MarsGame(), config);
	}
}
