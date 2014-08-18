package core.september.marstravel.screens.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import core.september.marstravel.gameobjects.Earth;
import core.september.marstravel.gameobjects.Fire;
import core.september.marstravel.gameobjects.Mars;
import core.september.marstravel.gameobjects.Rocket;
import core.september.marstravel.screens.controllers.GameController;
import core.september.marstravel.screens.controllers.GameController.O9InputListener;
import core.september.marstravel.utils.BaseB2D;
import core.september.marstravel.utils.BaseGameRenderer;
import core.september.marstravel.utils.Constants;

public class GameRenderer extends BaseGameRenderer{
	
	private final float rotationSpeed = 0.15f;
	private Earth earth;
	private Rocket rocket;
	private Mars mars;
	private Fire fire;
	private Box2DDebugRenderer sRenderer;
	
	private boolean zoomIn;
	
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
		addPlanets(world);
		addRocket(world);
		ortoCamera.translate(Constants.MAP_WIDTH / 2, earth.getHeight()*1.3f, 0);
	
	}
	
	private void addPlanets(World world) {
		earth = new Earth(ortoCamera, 0.6f,world);
		mars = new Mars(ortoCamera, 0.3f, world);
		//stage.addActor(earth);
	}

	private void addRocket(final World world) {
		rocket = new Rocket(ortoCamera,0.05f,world);
		fire = new Fire(rocket, 1f);
		
		
	}
	
	private Vector3 calulateForce(BaseB2D planet, Vector2 debrisPosition) {
		float radius = planet.getRadius();
		Vector2 planetPosition = planet.body.getWorldCenter();
		Vector2 planetDistance = new Vector2(0, 0);
		planetDistance.add(debrisPosition);
		planetDistance.sub(planetPosition);
		float finalDistance = planetDistance.len();
		planetDistance.x = -planetDistance.x;
		planetDistance.y = -planetDistance.y;
		float vecSum = Math.abs(planetDistance.x)+Math.abs(planetDistance.y);
		float multiplier = (vecSum)*radius/finalDistance;
		planetDistance.x*=multiplier;
		planetDistance.y*=multiplier;
		return new Vector3(planetDistance, finalDistance);
	}

	private void simulateGravity() {
		
		Vector2 debrisPosition = rocket.body.getWorldCenter();
		Vector3 earthForce = calulateForce(earth, debrisPosition);
		Vector3 marsForce = calulateForce(mars, debrisPosition);
		
		if(fire.fuelLevel > 0) {
			rocket.body.applyForce(new Vector2(earthForce.x, -earthForce.y*1.3f), debrisPosition, true);
			//zoomIn = false;
		}
		
		
		zoomIn = false;
		
		if (earthForce.z<=earth.getRadius()*3){
			rocket.body.applyForce(new Vector2(earthForce.x,earthForce.y),debrisPosition,true);
			zoomIn = true;
		}
		
		
		if (marsForce.z<=mars.getRadius()*3){
			rocket.body.applyForce(new Vector2(marsForce.x,marsForce.y),debrisPosition,true);
			zoomIn = true;
		}
//		Gdx.app.debug(TAG,"Planet distance x: "+planetDistance.x);
//		Gdx.app.debug(TAG,"Planet distance y: "+planetDistance.y);ortoCamera.update();
//		Gdx.app.debug(TAG,"Rocket distance x: "+debrisPosition.x);
//		Gdx.app.debug(TAG,"Rocket distance y: "+debrisPosition.y);
		
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
//	    
		simulateGravity();
	    world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
	    world.clearForces();
	    earth.update(deltaTime);
	    mars.update(deltaTime);
	    rocket.update(deltaTime);
	    fire.update(deltaTime);
	    handleZoom(deltaTime);
	    ortoCamera.update();
	}
	
	private float maxX = 0;
	private float minX = 9999;
	public void handleZoom(float deltaTime) {
		float zoom = ((OrthographicCamera) ortoCamera).zoom;
		if(!zoomIn && zoom < Constants.MAX_ZOOM) {
			((OrthographicCamera) ortoCamera).zoom+=deltaTime*0.5f;
			//Gdx.app.debug(TAG, "Zoom is now: "+ ((OrthographicCamera) ortoCamera).zoom);
		}
		if(zoomIn && zoom > Constants.MIN_ZOOM) {
			((OrthographicCamera) ortoCamera).zoom-=deltaTime*0.5f;
			//Gdx.app.debug(TAG, "Zoom is now: "+ ((OrthographicCamera) ortoCamera).zoom);
		}
		
		if(rocket.getY() > ortoCamera.viewportHeight/2) {
			float realY = ortoCamera.position.y;
			ortoCamera.translate(0, rocket.getY()- realY, 0);
		}
		
		
		
		float middleX = ortoCamera.viewportWidth/2;
		
		
		if(rocket.getX() < middleX - ortoCamera.viewportWidth) {
			float realX = ortoCamera.position.x;
			ortoCamera.translate(realX - rocket.getX() , 0, 0);
		}
		
		if(rocket.getX() > middleX + ortoCamera.viewportWidth ) {
			float realX = ortoCamera.position.x;
			ortoCamera.translate(rocket.getX() - realX , 0, 0);
		}
	
	}
	

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
		
		//stage.getCamera().apply(null);
		//stage.draw();
		
		mapRenderer.setView((OrthographicCamera) ortoCamera);
		
		mapRenderer.render();
		mapRenderer.getSpriteBatch().begin();
		earth.draw(mapRenderer.getSpriteBatch());
		mars.draw(mapRenderer.getSpriteBatch());
		rocket.draw(mapRenderer.getSpriteBatch());
		fire.draw(mapRenderer.getSpriteBatch());
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
