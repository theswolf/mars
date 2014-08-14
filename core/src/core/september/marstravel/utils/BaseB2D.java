package core.september.marstravel.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BaseB2D {

	public Body body;
	protected World world;
	protected float scale;
	protected Vector2 position;
	public BaseB2D(World world,float scale) {
		super();
		this.world = world;
		this.scale = scale;
	}
	
	public abstract Shape getShape();
	
	public abstract Vector2 getPosition() ;
	
	public Vector2 getSize() {
		return new Vector2(
				getTexture().getRegionWidth()*scale, 
				getTexture().getRegionHeight()*scale);
	}
	
	public abstract TextureRegion getTexture();
	public abstract void render(Batch batch);
	public abstract void update(float delta);
	
	public void createBody(BodyType bType,Float density,Float restitution,Float friction){//,float mass) {
		BodyDef bdef = new BodyDef();
		bdef.type = bType;
		//bdef.position.set(getX()*getScaleX(),getY()*getScaleY());
		bdef.position.set(getPosition());
		//bdef.fixedRotation = true;
		//bdef.linearVelocity.set(linearVelocity);
		
		// create body from bodydef
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = getShape();
		fdef.density = density;
		fdef.restitution = restitution;
		if(friction != null) {
			fdef.friction = friction;
		}
		
		
		body.createFixture(fdef);
		TextureRegion region = getTexture();
		if(region != null) {
			body.setUserData(getTexture());
		}
		
//		
//		MassData md = body.getMassData();
//		md.mass = mass;
//		body.setMassData(md);
	}
}
