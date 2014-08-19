package core.september.marstravel.screens.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

import core.september.marstravel.screens.controllers.GameController.O9InputListener;
import core.september.marstravel.utils.BaseGameController;

public class TestController extends BaseGameController {

	public interface O9InputListener{
		boolean keyDown(int keycode);
		boolean keyUp(int keycode);
	}
	private InputAdapter iAdapter;
	private O9InputListener listener;
	public TestController(Game game) {
		super(game);
		iAdapter = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				return listener.keyDown(keycode);
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return listener.keyUp(keycode);
			}
		};
		Gdx.input.setInputProcessor(iAdapter);
	}

	public void setListener(O9InputListener listener) {
		this.listener = listener;
	}
}
