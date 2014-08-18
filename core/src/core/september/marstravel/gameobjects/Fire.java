package core.september.marstravel.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;

import core.september.marstravel.utils.Assets;
import core.september.marstravel.utils.BaseB2D;
import core.september.marstravel.utils.Constants;

public class Fire extends BaseB2D {
	protected final static String TAG = Fire.class.getSimpleName();
	private float delta;
	public float fuelLevel = Constants.FUEL_LEVEL;
	//private Animation animationFireStart;
	private Animation fireLoop;
	private Rocket rocket;
	//private boolean fromBegin = true;
	//private Sprite fireSprite;
	private TextureRegion[] fire;//da 22 a 6 // 9 a 6 in loop
	public Fire(Rocket rocket,float scale) {
		
		super(null,scale,Assets.instance().RES_FIRE[0]);
		fire = Assets.instance().RES_FIRE;
		this.rocket = rocket;
		setPosition(rocket.getX() - getWidth()/2 - rocket.getWidth() /2 + 20
				,rocket.getY() - getHeight()/2 - rocket.getHeight() /2 - 20);
		fireLoop = getRange(fire,6,9,PlayMode.LOOP_REVERSED);
	}
	
	
	
	@Override
	public void update(float delta) {
		if(fuelLevel > 0) {
			fuelLevel-=delta;
			Gdx.app.debug(TAG,"fuelLevel is: "+fuelLevel);
			setPosition(rocket.getX() - getWidth()/2 - rocket.getWidth() /2 + 20
			,rocket.getY() - getHeight()/2 - rocket.getHeight() /2 - 20);
			this.delta += delta;
//			setPosition(rocket.getX() - rocket.getWidth()/2 - getWidth()/2
//					, rocket.getY() - rocket.getHeight()/2 - getHeight()/2);
			//Vector2 origin = rocket.getOrigin();
			setOrigin(rocket.getOriginX(),rocket.getOriginY());
			setRotation(rocket.getRotation());
		}
		
	}
	
	@Override
	public void draw(Batch batch) {
		if(fuelLevel > 0) {
		setRegion(fireLoop.getKeyFrame(delta));
		super.draw(batch);
		}
		
	}
	
	
	private Animation getRange(TextureRegion[] regions, int start, int end, PlayMode mode) {
		TextureRegion[] retText = new TextureRegion[end-start+1];
		
		int loop = 0;
		for(TextureRegion region: regions) {
			int number = Integer.parseInt(((AtlasRegion)region).name.replaceAll("[^0-9]", ""));
			if(start <= number && number <= end) {
				retText[loop] = region;
				loop++;
			}
		}
		
		Animation ret = new Animation(0.1f, retText);
		ret.setPlayMode(mode);
		return ret;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

//	
//	@Override
//	public Vector2 getPosition() {
//		// TODO Auto-generated method stub
//		return position;
//	}

//	@Override
//	public TextureRegion getTexture() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}