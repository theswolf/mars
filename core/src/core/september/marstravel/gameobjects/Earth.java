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

public class Earth extends BaseB2D{
	private TextureRegion earth;
	public Earth(Camera camera,float scale, World world) {
		super(world,scale);
		earth = Assets.instance().RES_EARTH[0];
		//Gdx.gl20.GL_VIEWPORT;
		//Viewport currentViewport = Reference.instance().getCurrentViewport();
		position = new Vector2(Constants.MAP_WIDTH / 2 - earth.getRegionWidth()*scale/2
				, Constants.MAP_HEIGHT / 2 - earth.getRegionHeight()*scale * 1.2f);
		createBody(BodyType.StaticBody, 1f, 0f,null);//, 100);
		
	
	}
	
	@Override
	public Vector2 getPosition() {
		Vector2 posNew = new Vector2(position);
		return posNew.add(new Vector2(earth.getRegionWidth()*scale / 2, earth.getRegionHeight()*scale / 2));
	}
	
	@Override
    public void render (Batch batch) {
        batch.draw(earth, position.x,position.y,
        		earth.getRegionWidth()*scale,earth.getRegionHeight()*scale);
      

    }

	@Override
	public Shape getShape() {
		CircleShape shape = new CircleShape();
		shape.setRadius(earth.getRegionWidth()*scale / 2);
		return shape;
	}

	@Override
	public TextureRegion getTexture() {
		return earth;
	}

	
	
	@Override
	public void update(float delta) {
		//position = body.getPosition();
	}
}
