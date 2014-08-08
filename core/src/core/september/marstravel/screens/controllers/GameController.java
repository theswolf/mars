package core.september.marstravel.screens.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import core.september.marstravel.utils.BaseGameController;

public class GameController extends BaseGameController{

	public interface O9InputListener{
		boolean keyDown(int keycode);
	}
	
	private InputAdapter iAdapter;
	private O9InputListener listener;
	public GameController(Game game) {
		super(game);
		iAdapter = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				return listener.keyDown(keycode);
			}
		};
		Gdx.input.setInputProcessor(iAdapter);
	}

	public void setListener(O9InputListener listener) {
		this.listener = listener;
	}

}
