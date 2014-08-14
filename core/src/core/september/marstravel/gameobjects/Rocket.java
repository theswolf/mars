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

	private Sprite rocket;
	

//	private Camera camera;
//	private float scale;
	

	
	public Rocket(Camera camera,float scale, World world) {
		super(world,scale);
		rocket = new Sprite(Assets.instance().RES_ROCKET[0]);
		
		
		position = new Vector2(Constants.MAP_WIDTH / 2 - rocket.getRegionWidth()*scale/2
				, Constants.MAP_HEIGHT / 2 - rocket.getRegionHeight()*scale/2);
		rocket.setPosition(position.x,position.y);
		rocket.setSize(rocket.getRegionWidth()*scale, rocket.getRegionHeight()*scale);
		createBody(BodyType.DynamicBody,1f, 0f, 1f);
	
	}
	
	
	
	@Override
    public void render (Batch batch){
//		  batch.draw(rocket, position.x,position.y,
//				  rocket.getRegionWidth()*scale,rocket.getRegionHeight()*scale);
		rocket.draw(batch);

    }

	@Override
	public Vector2 getPosition() {
		return position;
	}


	@Override
	public Shape getShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(rocket.getRegionWidth()/2*scale
				, rocket.getRegionHeight()/2*scale);
		return shape;
	}



	@Override
	public TextureRegion getTexture() {
		return rocket;
	}



	@Override
	public void update(float delta) {
		position = body.getPosition().sub(rocket.getRegionWidth()*scale /2, rocket.getRegionHeight()*scale/2);
		rocket.setPosition(position.x, position.y);
		rocket.setOriginCenter();
		rocket.setRotation(body.getAngle()*MathUtils.radiansToDegrees);
	}
	
	
	
	
		
}
