package core.september.marstravel.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseActorB2D extends Actor{

	public Body body;
	protected World world;
	
	public BaseActorB2D(World world) {
		super();
		this.world = world;
	}
	
	public abstract Shape getShape();
	public abstract TextureRegion getTexture();
	
	public void createBody(Float density,Float restitution,Float friction){//,float mass) {
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		//bdef.position.set(getX()*getScaleX(),getY()*getScaleY());
		bdef.position.set(getX(),getY());
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
