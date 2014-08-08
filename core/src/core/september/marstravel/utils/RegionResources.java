package core.september.marstravel.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import core.september.marstravel.utils.ifaces.ResourceLifeCycle;

public abstract class RegionResources {
	
	public AtlasRegion[] regions;
	
	public RegionResources(String [] resources, TextureAtlas atlas) {
		regions = new AtlasRegion[resources.length];
		int pos=0;
		for(String resource : resources) {
			regions[pos] = atlas.findRegion(resource);
			pos++;
		}
	}
	



}
