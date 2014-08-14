package core.september.marstravel.screens.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import core.september.marstravel.gameobjects.Earth;
import core.september.marstravel.gameobjects.Fire;
import core.september.marstravel.gameobjects.Rocket;
import core.september.marstravel.screens.controllers.GameController;
import core.september.marstravel.screens.controllers.GameController.O9InputListener;
import core.september.marstravel.utils.BaseGameRenderer;
import core.september.marstravel.utils.Constants;

public class GameRenderer extends BaseGameRenderer{
	
	private final float rotationSpeed = 0.15f;
	private Earth earth;
	private Rocket rocket;
	private Box2DDebugRenderer sRenderer;
	
	private World world;
	private final static String TAG = GameRenderer.class.getSimpleName();
	public GameRenderer(GameController controller) {
		super(controller);
		
		sRenderer = new Box2DDebugRenderer();
		controller.setListener(new O9InputListener() {
			
			@Override
			public boolean keyDown(int keycode) {
				//OrthographicCamera cam = (OrthographicCamera) stage.getCamera();
				if(Gdx.input.isKeyPressed(Input.Keys.A)) {
		            ((OrthographicCamera)ortoCamera).zoom += 0.02;
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
		        	 ((OrthographicCamera)ortoCamera).zoom -= 0.02;
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
		            if ( ((OrthographicCamera)ortoCamera).position.x > Constants.GAME_WIDTH / 2)
		            	 ((OrthographicCamera)ortoCamera).translate(-3, 0, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
		            if ( ((OrthographicCamera)ortoCamera).position.x <  Constants.MAP_WIDTH - Constants.GAME_WIDTH / 2)
		            	 ((OrthographicCamera)ortoCamera).translate(3, 0, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
		            if ( ((OrthographicCamera)ortoCamera).position.y > Constants.GAME_HEIGHT / 2)
		            	 ((OrthographicCamera)ortoCamera).translate(0, -3, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
		            if ( ((OrthographicCamera)ortoCamera).position.y < Constants.MAP_HEIGHT - Constants.GAME_HEIGHT / 2)
		            	 ((OrthographicCamera)ortoCamera).translate(0, 3, 0);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
		        	 ((OrthographicCamera)ortoCamera).rotate(-rotationSpeed, 0, 0, 1);
		        }
		        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
		        	 ((OrthographicCamera)ortoCamera).rotate(rotationSpeed, 0, 0, 1);
		        }
		        ((OrthographicCamera)ortoCamera).update();
		        return true;
			}
		});
		
		world = new World(new Vector2(0, 0), true);
		ortoCamera.translate(Constants.MAP_WIDTH / 2, Constants.MAP_HEIGHT / 2, 0);
		addPlanets(world);
		addRocket(world);
	}
	
	private void addPlanets(World world) {
		earth = new Earth(ortoCamera, 0.6f,world);
		//stage.addActor(earth);
	}

	private void addRocket(final World world) {
		rocket = new Rocket(ortoCamera,0.05f,world);
		Fire fire = new Fire(rocket, 1f);
		
		
	}

	private void simulateGravity() {
		
		Vector2 debrisPosition = rocket.body.getWorldCenter();
		
		Shape planetShape = earth.body.getFixtureList().get(0).getShape();
		float radius = planetShape.getRadius();
		Vector2 planetPosition = earth.body.getWorldCenter();
		Vector2 planetDistance = new Vector2(0, 0);
		planetDistance.add(debrisPosition);
		planetDistance.sub(planetPosition);
//		var finalDistance:Number=planetDistance.Length();
//		if (finalDistance<=planetRadius*3) {
//			planetDistance.NegativeSelf();
//			var vecSum:Number=Math.abs(planetDistance.x)+Math.abs(planetDistance.y);
//			planetDistance.Multiply((1/vecSum)*planetRadius/finalDistance);
//			debrisVector[i].ApplyForce(planetDistance,debrisVector[i].GetWorldCenter());
//		}
		float finalDistance = planetDistance.len();
		planetDistance.x = -planetDistance.x;
		planetDistance.y = -planetDistance.y;
		float vecSum = Math.abs(planetDistance.x)+Math.abs(planetDistance.y);
		float multiplier = (vecSum)*radius/finalDistance;
		planetDistance.x*=multiplier;
		planetDistance.y*=multiplier;
		rocket.body.applyForce(planetDistance,debrisPosition,true);
		Gdx.app.debug(TAG,"Planet distance x: "+planetDistance.x);
		Gdx.app.debug(TAG,"Planet distance y: "+planetDistance.y);ortoCamera.update();
		Gdx.app.debug(TAG,"Rocket distance x: "+debrisPosition.x);
		Gdx.app.debug(TAG,"Rocket distance y: "+debrisPosition.y);
		
	}
	private float accumulator = 0;
	@Override
	public void update(float deltaTime) {
//		 float frameTime = Math.min(deltaTime, 0.25f);
//		    accumulator += frameTime;
//		    while (accumulator >= Constants.TIME_STEP) {
//		    	simulateGravity();
//		        world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
//		        //world.clearForces();
//		        accumulator -= Constants.TIME_STEP;
//		    }
	    simulateGravity();
	    world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
	    world.clearForces();
	    earth.update(deltaTime);
	    rocket.update(deltaTime);
	    //world.clearForces();
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
		
		//stage.getCamera().apply(null);
		//stage.draw();
		
		mapRenderer.setView((OrthographicCamera) ortoCamera);
		ortoCamera.update();
		mapRenderer.render();
		mapRenderer.getSpriteBatch().begin();
		earth.render(mapRenderer.getSpriteBatch());
		rocket.render(mapRenderer.getSpriteBatch());
		mapRenderer.getSpriteBatch().end();
		sRenderer.render(world, ortoCamera.combined);
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
