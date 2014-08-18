package core.september.marstravel.gameobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import core.september.marstravel.utils.Assets;
import core.september.marstravel.utils.BaseB2D;
import core.september.marstravel.utils.Constants;

public class Rocket extends BaseB2D{

	//private Sprite rocket;
	

//	private Camera camera;
//	private float scale;
	

	
	public Rocket(Camera camera,float scale, World world) {
		super(world,scale,Assets.instance().RES_ROCKET[0]);
		//this(Assets.instance().RES_ROCKET[0]);
		setPosition(Constants.MAP_WIDTH / 2 -getWidth()/2 ,320);
		createBody(BodyType.DynamicBody,1f, 0f, 1f);
	
	}
	
	

//	@Override
//	public Vector2 getPosition() {
//		return position;
//	}


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


	public Vector2 getOrigin() {
		return new Vector2(getOriginX(),getOriginY());
	}
	
	

	@Override
	public void update(float delta) {
		//position = body.getPosition().sub(getRegionWidth()*scale /2, getRegionHeight()*scale/2);
		setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
		setOriginCenter();
		setRotation(body.getAngle()*MathUtils.radiansToDegrees);
	}
	
	
	
	
		
}
