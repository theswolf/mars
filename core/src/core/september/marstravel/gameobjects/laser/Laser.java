package core.september.marstravel.gameobjects.laser;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import core.september.marstravel.utils.Assets;

public class Laser {
	
	public Vector2 position ;
	public float distance;
	public Color color = new Color(Color.ORANGE);
	public Color rayColor = new Color(Color.RED);
	public float degrees;
	//private LaserTest lt = LaserTest.get();
	public Sprite begin1,begin2,mid1,mid2,end1,end2;
	public Vector2 origin;

	public Laser(float scale,Vector2 origin) {
		this.origin = origin;
		position = new Vector2();
		begin1 = new Sprite(buildLaser(scale,Assets.instance().RES_LASER[0],origin));
		begin2 = new Sprite(buildLaser(scale,Assets.instance().RES_LASER[1],origin));
		mid1 = new Sprite(buildLaser(scale,Assets.instance().RES_LASER[2],origin));
		mid2 = new Sprite(buildLaser(scale,Assets.instance().RES_LASER[3],origin));
		end1 = new Sprite(buildLaser(scale,Assets.instance().RES_LASER[4],origin));
		end2 = new Sprite(buildLaser(scale,Assets.instance().RES_LASER[5],origin));
	}
	
	private Sprite buildLaser(float scale, AtlasRegion region,Vector2 origin) {
		Sprite sp = new Sprite(region);
		sp.setSize(region.getRegionWidth()*scale, region.getRegionHeight()*scale);
		return sp;
	}
	
//	public void setOrigin(Vector2 origin) {
//		begin1.setOrigin(origin.x, origin.y);
//		begin2.setOrigin(origin.x, origin.y);
//		mid1.setOrigin(origin.x, origin.y);
//		mid2.setOrigin(origin.x, origin.y);
//		end1.setOrigin(origin.x, origin.y);
//		end2.setOrigin(origin.x, origin.y); 
//	}
//	public void evaluatePosition(BaseB2D father) {
//		Vector2 fatherPos = new Vector2( father.body.getPosition().x-father.getWidth()/2, father.body.getPosition().y-father.getHeight()/2);
//		fatherPos.add(begin1.getWidth()/2, 10);
//	}

	
	private void calculateOrigin(Vector2 start) {
		begin1.setOrigin(start.x,start.y);
		begin2.setOrigin(start.x,start.y);


		mid1.setOrigin(start.x, -begin1.getHeight()+start.y);
		mid2.setOrigin(start.x, -begin1.getHeight() +start.y);
		end1.setOrigin(start.x, -begin1.getHeight()-mid1.getHeight() +start.y);
		end2.setOrigin(start.x, -begin1.getHeight()-mid2.getHeight() +start.y);
	}
	
	
	private void calculatePosition(int pressed) {
		//1 left 2 right
		position.x = begin1.getWidth()/2 - origin.x -10 ;
		position.y = begin1.getHeight()/2 - origin.y +20;
		switch (pressed) {
		case 1:
			position.sub(10, 0);
			break;

		case 2:
			position.add(10, 0);
			break;
		}
		position.rotate(degrees);
	}
	
	public void render(Batch batch, int pressed) {
		begin1.setColor(color);
		begin2.setColor(rayColor);
		mid1.setColor(color);
		mid2.setColor(rayColor);
		end1.setColor(color);
		end2.setColor(rayColor);

		mid1.setSize(mid1.getWidth(), distance);
		mid2.setSize(mid1.getWidth(), distance);

		
		calculateOrigin(origin);
		calculatePosition(pressed);

		begin1.setRotation(degrees);
		begin2.setRotation(degrees);
		mid1.setRotation(degrees);
		mid2.setRotation(degrees);
		end1.setRotation(degrees);
		end2.setRotation(degrees);
		
		
		begin1.setPosition(position.x, position.y);
		begin2.setPosition(position.x, position.y);

		mid1.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight());
		mid2.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight());

		end1.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight()+mid1.getHeight());
		end2.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight()+mid1.getHeight());
		


		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

		begin1.draw(batch);
		begin2.draw(batch);


		mid1.draw(batch);

		mid2.draw(batch);

		end1.draw(batch);
		end2.draw(batch);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


	}
}
