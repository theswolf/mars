package core.september.marstravel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import core.september.marstravel.screens.PlayScreen;
import core.september.marstravel.utils.Assets;

public class MarsGame extends Game {
	SpriteBatch batch;
	//Texture img;
	
	Assets assets;
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance();
		setScreen(new PlayScreen(this));
	}

	
	
}
