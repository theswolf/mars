package core.september.marstravel.gameobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import core.september.marstravel.utils.Assets;

public class Rocket extends Actor{

	private TextureRegion rocket;
//	private Camera camera;
//	private float scale;
	
	public Rocket(Camera camera,float scale) {
		super();
		rocket = Assets.instance().RES_ROCKET[0];
		setWidth(rocket.getRegionWidth());
		setHeight(rocket.getRegionHeight());
		setX(camera.position.x - (rocket.getRegionWidth() / 2));
		setY(camera.position.y - (rocket.getRegionHeight() / 2));
//		setX(camera.position.x);
//		setY(camera.position.y + 10);
		setOrigin(getWidth()/2, getHeight()/2);
		setScale(scale);
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(rocket, getX(), getY(), getOriginX(), getOriginY(),
            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
	
}
