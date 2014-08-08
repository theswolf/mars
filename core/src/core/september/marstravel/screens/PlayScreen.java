package core.september.marstravel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.javafx.binding.SelectBinding.AsString;

import core.september.marstravel.screens.controllers.GameController;
import core.september.marstravel.screens.renderers.GameRenderer;
import core.september.marstravel.utils.Assets;
import core.september.marstravel.utils.BaseGameScreen;
import core.september.marstravel.utils.Constants;

public class PlayScreen extends BaseGameScreen {


	
	
	public PlayScreen(Game game) {
		super(game);
		
	}

	
	


	@Override
	protected void prepareForShow() {
		controller = new GameController(game);
		renderer = new GameRenderer((GameController) controller);
		
	}

	


	

	


}
