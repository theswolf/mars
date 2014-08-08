package core.september.marstravel.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class BaseGameScreen  implements Screen{

	protected boolean isPaused = false;
	
	protected BaseGameController controller;
	protected BaseGameRenderer renderer;
	protected Game game;
	
	public BaseGameScreen(Game game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		if(!isPaused) {
			renderer.update(delta);
		}
		
		renderer.render();
		
	}
	
	@Override
	public void resume() {
		isPaused = false;
		Assets.instance();
	}

	@Override
	public void dispose() {
		pause();
		renderer.dispose();
		Assets.instance().dispose();
	}
	
	@Override
	public void pause() {
		isPaused = true;
		
	}
	@Override
	public void resize(int width, int height) {
		//stage.getViewport().update(width, height);
		renderer.resize(width,height);
		
	}

	@Override
	public void show() {
		prepareForShow();
		Gdx.input.setCatchBackKey(true);
		
	}
	

	protected abstract void prepareForShow();

	@Override
	public void hide() {
		renderer.dispose();
		Gdx.input.setCatchBackKey(false);
	}

	
}
