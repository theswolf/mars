package core.september.marstravel.screens.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import core.september.marstravel.gameobjects.Rocket;
import core.september.marstravel.screens.controllers.GameController;
import core.september.marstravel.screens.controllers.GameController.O9InputListener;
import core.september.marstravel.utils.BaseGameRenderer;
import core.september.marstravel.utils.Constants;

public class GameRenderer extends BaseGameRenderer{
	
	private final float rotationSpeed = 0.15f;

	public GameRenderer(GameController controller) {
		super(controller);
		controller.setListener(new O9InputListener() {
			
			@Override
			public boolean keyDown(int keycode) {
				OrthographicCamera cam = (OrthographicCamera) stage.getCamera();
				if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		            cam.zoom += 0.02;
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
		            cam.zoom -= 0.02;
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
		            if (cam.position.x > Constants.GAME_WIDTH / 2)
		                cam.translate(-3, 0, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
		            if (cam.position.x <  Constants.MAP_WIDTH - Constants.GAME_WIDTH / 2)
		                cam.translate(3, 0, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
		            if (cam.position.y > Constants.GAME_HEIGHT / 2)
		                cam.translate(0, -3, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
		            if (cam.position.y < Constants.MAP_HEIGHT - Constants.GAME_HEIGHT / 2)
		                cam.translate(0, 3, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
		            cam.rotate(-rotationSpeed, 0, 0, 1);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
		            cam.rotate(rotationSpeed, 0, 0, 1);
		        }
		        cam.update();
		        return true;
			}
		});
		
		stage.getCamera().translate(Constants.MAP_WIDTH / 2, 0, 0);
		addRocket();
	}

	private void addRocket() {
		Rocket rocket = new Rocket(stage.getCamera(),0.05f);
		stage.addActor(rocket);
	}

	@Override
	public void update(float deltaTime) {
		stage.act(deltaTime);
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
		
		//stage.getCamera().apply(null);
		//stage.draw();
		mapRenderer.setView((OrthographicCamera) stage.getCamera());
		mapRenderer.render();
		stage.draw();
		
//		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		camera.update();
//		renderer.setView(camera);
//		renderer.render();
//		batch.begin();
//		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
//		batch.end();
		
	}
	
	

}
