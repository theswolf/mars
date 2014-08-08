package core.september.marstravel.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;

public abstract class BaseGameRenderer implements Disposable{

	
	BaseGameController controller;
	protected Stage stage;
	float unitScale = 1;
	protected OrthogonalTiledMapRenderer mapRenderer;
	protected Camera ortoCamera;
	
	public BaseGameRenderer(BaseGameController controller) {
		this.controller = controller;
		ortoCamera = new OrthographicCamera(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		stage = new Stage(new FillViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
		mapRenderer = new OrthogonalTiledMapRenderer(Assets.instance().MAP, unitScale);
		//mapRenderer.setView((OrthographicCamera) stage.getCamera());
	}
	
	@Override
	public void dispose() {
		mapRenderer.dispose();
		stage.dispose();
		
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
		
	}
	
	public abstract void update(float deltaTime);
	public abstract void render();
	

}
