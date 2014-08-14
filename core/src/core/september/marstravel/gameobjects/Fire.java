package core.september.marstravel.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import core.september.marstravel.utils.Assets;

public class Fire extends Actor {
	private float delta;
	//private Animation animationFireStart;
	private Animation fireLoop;
	//private boolean fromBegin = true;
	private Fire fireActor;
	private TextureRegion[] fire;//da 22 a 6 // 9 a 6 in loop
	public Fire(Rocket rocket,float scale) {
		super();
		fire = Assets.instance().RES_FIRE;
		setWidth(fire[0].getRegionWidth());
		setHeight(fire[0].getRegionHeight());
//		setX(rocket.getCenterX() -(fire[0].getRegionWidth() / 2));
//		setY(rocket.getCenterY() - fire[0].getRegionHeight() * 1.1f);//- ( fire[0].getRegionHeight() / 2 ) - (rocket.getHeight()/ 2));
//		setOrigin(rocket.getOriginX(),rocket.getOriginY());
		//setOrigin(getWidth()/2, getHeight()/2);
		setScale(scale);
		
		//animationFireStart = getRange(fire, 17, 22,PlayMode.REVERSED);
		fireLoop = getRange(fire,6,9,PlayMode.LOOP_REVERSED);
	}
	
	@Override
	public void act(float delta) {
	// TODO Auto-generated method stub
	super.act(delta);
	this.delta += delta;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        	batch.draw(fireLoop.getKeyFrame(delta), getX() , getY() , getOriginX(), getOriginY(),
 		            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		
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
}