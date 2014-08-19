package core.september.marstravel.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import core.september.marstravel.utils.ifaces.ResourceLifeCycle;

public class Assets implements ResourceLifeCycle{
	
	private static Assets assets;
	
	private AssetManager assetManager;
	
	public final  AtlasRegion[] RES_LOGO;
	public final  AtlasRegion[] RES_ROCKET;
	public final   AtlasRegion[] RES_FIRE;
	public final   AtlasRegion[] RES_BOOM; 
	public final   AtlasRegion[] RES_MARS; 
	public final   AtlasRegion[] RES_MOON; 
	public final   AtlasRegion[] RES_EARTH; 
	public final   AtlasRegion[] RES_LASER; 
	public final   TiledMap MAP;
	
	public static Assets instance() {
		if(assets == null) {
			assets = new Assets();
		}
		return assets;
	}
	
	private Assets() {
		this.create();
		RES_LOGO = getRegion(Constants.Resources.LOGO.res);     
		RES_ROCKET = getRegion(Constants.Resources.ROCKET.res); 
		RES_FIRE = getRegion(Constants.Resources.FIRE.res);     
		RES_BOOM = getRegion(Constants.Resources.BOOM.res);     
		RES_MARS = getRegion(Constants.Resources.MARS.res);     
		RES_MOON =getRegion( Constants.Resources.MOON.res);     
		RES_EARTH = getRegion(Constants.Resources.EARTH.res);
		RES_LASER = getRegion(Constants.Resources.LASER.res);
		MAP = assetManager.get(Constants.MAP);
		
	}

	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.load(Constants.ATLAS, TextureAtlas.class);
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load(Constants.MAP, TiledMap.class);
		assetManager.finishLoading();
//		atlas = assetManager.get(Constants.ATLAS);
//		for(AtlasRegion region: atlas.getRegions()) {
//			System.out.println("=\""+region.name+"\";");
//		}
		
		//AtlasRegion region = atlas.findRegion("imagename");
		//Sprite sprite = atlas.createSprite("otherimagename");
		//NinePatch patch = atlas.createPatch("patchimagename");
		
	}

	private AtlasRegion[] getRegion(String[] resNames) {
		AtlasRegion[] atReg = new AtlasRegion[resNames.length];
		int loop = 0;
		for(String resName: resNames) {
			atReg[loop] = ((TextureAtlas)assetManager.get(Constants.ATLAS)).findRegion(resName);
			loop++;
		}
		return atReg;
	}
	

	@Override
	public void dispose() {
		assetManager.dispose();
		if(assets != null) assets = null;
	}
	
}
