package core.september.marstravel.gameobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import core.september.marstravel.gameobjects.laser.Laser;
import core.september.marstravel.utils.Assets;
import core.september.marstravel.utils.BaseB2D;
import core.september.marstravel.utils.Constants;

public class Rocket extends BaseB2D{

	//private Sprite rocket;
	

//	private Camera camera;
//	private float scale;
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int RIGHT = 2;
	public static int NOT_PRESSED = -1;
	
	private int pressed = NOT_PRESSED;
	
	//private final Pool<Laser> laserPool ;
	public Laser currentLaser;
	
	public Rocket(Camera camera,float scale, World world) {
		super(world,scale,Assets.instance().RES_ROCKET[0]);
		//this(Assets.instance().RES_ROCKET[0]);
		setPosition(Constants.MAP_WIDTH / 2 -getWidth()/2 ,320);
		createBody(BodyType.DynamicBody,1f, 0f, 1f);
	}
	
	public void calculateLaserRotation(int position) {
		//currentLaser.setOrigin(getOrigin());
		switch (position) {
		case 0:
			currentLaser.degrees = getRotation() + 180;
			break;
		case 1:
			currentLaser.degrees = getRotation() + 180 - 45;
			break;
		case 2:
			currentLaser.degrees = getRotation() + 180 + 45;
			break;
		}
	}
	
	public void applyPressure(int position) {
		currentLaser = new Laser(0.5f, getOrigin());
		currentLaser.distance = 100;
		Vector2 adjustedPosition = adjustedPosition();
		calculateLaserRotation(position);
		
		//currentLaser.color = Color.ORANGE;
		pressed = position;
	}
	
	public void unpress() {
		pressed = NOT_PRESSED;
		if(currentLaser != null) {
			currentLaser = null;
		}
		
	}
//	@Override
//	public Vector2 getPosition() {
//		return position;
//	}

	@Override
	public void draw(Batch batch) {
		if(pressed != NOT_PRESSED) {
			drawLaser(batch,pressed);
		}
		super.draw(batch);
	}
	
	private void drawLaser(Batch batch,int pressed) {
		currentLaser.render(batch,pressed);
	}

	@Override
	public Shape getShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth()/2
				, getHeight()/2);
		return shape;
	}

	public Sprite getSprite() {
		return this;
	}
//
//	@Override
//	public void rotate (float degrees) {
//		super.rotate(degrees);
//		if(currentLaser != null) {
//			currentLaser.origin.rotate(degrees);
//		}
//	}

	public Vector2 getOrigin() {
		return new Vector2(getOriginX(),getOriginY());
	}
	
//	

	@Override
	public void update(float delta) {
		//position = body.getPosition().sub(getRegionWidth()*scale /2, getRegionHeight()*scale/2);
		setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
		setOriginCenter();
		if(currentLaser != null) {
			//calculateLaserPosition(pressed);
			float angularForce = Constants.SPRAY_PROPULSION * delta;
			currentLaser.origin = getOrigin();
			calculateLaserRotation(pressed);
			body.applyAngularImpulse(angularForce, true);
		}
		
		setRotation(body.getAngle()*MathUtils.radiansToDegrees);
	}
	
	
}
	
		
