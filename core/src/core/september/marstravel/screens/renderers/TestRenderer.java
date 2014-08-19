package core.september.marstravel.screens.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import core.september.marstravel.gameobjects.Rocket;
import core.september.marstravel.screens.controllers.TestController;
import core.september.marstravel.screens.controllers.TestController.O9InputListener;
import core.september.marstravel.utils.BaseGameRenderer;

public class TestRenderer extends BaseGameRenderer {

	private Rocket rocket;
	private World world;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	public TestRenderer(TestController controller) {
		super(controller);
		controller.setListener(new O9InputListener() {
			
			@Override
			public boolean keyUp(int keycode) {
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
			
		        if(Gdx.input.isKeyPressed(Input.Keys.LEFT )) {
		           rocket.applyPressure(Rocket.LEFT);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
		             rocket.applyPressure(Rocket.RIGHT);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
		        	 rocket.applyPressure(Rocket.UP);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.U)) {
		        	rocket.unpress();
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
		        	rocket.rotate(15);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.R)) {
		        	rocket.rotate(-15);
		        }
		        
		        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		        	((OrthographicCamera)ortoCamera).zoom +=1; 
		        }
		        
		        if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
		        	((OrthographicCamera)ortoCamera).zoom -=1; 
		        }
		        
		        ((OrthographicCamera)ortoCamera).update();
		        return true;
			}
		});
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		world = new World(new Vector2(0, 0), true);
		rocket = new Rocket(ortoCamera,0.05f,world);
		//ortoCamera.translate(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, 0);
		rocket.setPosition(0,0);
		rocket.createBody(BodyType.DynamicBody,1f, 0f, 1f);
	}

	@Override
	public void update(float deltaTime) {
		float angle = rocket.getRotation();
		rocket.update(deltaTime);
		rocket.setRotation(angle);
		ortoCamera.update();
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(ortoCamera.projection);
		batch.setTransformMatrix(ortoCamera.view);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		rocket.draw(batch);
		batch.end();
		shapeRenderer.setProjectionMatrix(ortoCamera.combined);
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(rocket.getOriginX(), rocket.getOriginY(), 1);
        if(rocket.currentLaser != null) {
        	shapeRenderer.setColor(Color.BLUE);
        	shapeRenderer.circle(rocket.currentLaser.begin1.getOriginX(), rocket.currentLaser.begin1.getOriginY(), 1);
        }
        
        shapeRenderer.end();

	}

}
