package core.september.marstravel.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BaseB2D extends Sprite{

	public Body body;
	protected World world;
	//public float scale;
	public BaseB2D(World world,float scale,AtlasRegion region) {
		super(region);
		this.world = world;
		setSize(getRegionWidth()*scale, getRegionHeight()*scale);
		setOrigin(getWidth()/2,getHeight()/2);
	}
	
	public abstract Shape getShape();
	
	
//	public Vector2 getSize() {
//		return new Vector2(
//				getRegionWidth()*scale, 
//				getRegionWidth()*scale);
//	}
	
	//public abstract TextureRegion getTexture();
	public abstract void update(float delta);
	
	public float getRadius() {
		Shape planetShape = body.getFixtureList().get(0).getShape();
		return  planetShape.getRadius();
		
	}
	
	public Vector2 adjustedPosition() {
		return new Vector2(getX()+getWidth()/2, getY()+getHeight()/2);
	}
	
	public void createBody(BodyType bType,Float density,Float restitution,Float friction){//,float mass) {
		BodyDef bdef = new BodyDef();
		bdef.type = bType;
		Vector2 adjustedPosition = adjustedPosition();
		bdef.position.set(adjustedPosition.x,adjustedPosition.y);
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = getShape();
		fdef.density = density;
		fdef.restitution = restitution;
		if(friction != null) {
			fdef.friction = friction;
		}
		
		
		body.createFixture(fdef);
		Texture region = getTexture();
		if(region != null) {
			body.setUserData(getTexture());
		}
		
//		
//		MassData md = body.getMassData();
//		md.mass = mass;
//		body.setMassData(md);
	}
}
