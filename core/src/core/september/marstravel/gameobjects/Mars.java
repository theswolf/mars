package core.september.marstravel.gameobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import core.september.marstravel.utils.Assets;
import core.september.marstravel.utils.BaseB2D;
import core.september.marstravel.utils.Constants;

public class Mars extends BaseB2D{
	//private TextureRegion mars;
	public Mars(Camera camera,float scale, World world) {
		super(world,scale,Assets.instance().RES_MARS[0]);
		//Gdx.gl20.GL_VIEWPORT;
		//Viewport currentViewport = Reference.instance().getCurrentViewport();
	
		setPosition(Constants.MAP_WIDTH / 2 - getWidth()/2 + 10, Constants.MAP_HEIGHT / 2);
		createBody(BodyType.StaticBody, 1f, 1f,null);//, 100);
		
	
	}
	
//	@Override
//	public Vector2 getPosition() {
//		Vector2 posNew = new Vector2(position);
//		return posNew.add(new Vector2(getRegionWidth()*scale / 2, getRegionHeight()*scale / 2));
//	}
	
//	@Override
//    public void render (Batch batch) {
//        batch.draw(mars, position.x,position.y,
//        		mars.getRegionWidth()*scale,mars.getRegionHeight()*scale);
//      
//
//    }

	@Override
	public Shape getShape() {
		CircleShape shape = new CircleShape();
		shape.setRadius(getWidth() / 2);
		return shape;
	}

//	@Override
//	public TextureRegion getTexture() {
//		return mars;
//	}

	
	
	@Override
	public void update(float delta) {
		//position = body.getPosition();
	}
}
