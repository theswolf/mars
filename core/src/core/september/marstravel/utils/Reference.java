package core.september.marstravel.utils;

import java.lang.ref.WeakReference;

import com.badlogic.gdx.utils.viewport.Viewport;

public class Reference {

	protected WeakReference<Viewport> currentViewport;
	
	private static Reference ref;
	private Reference() {
		
	}
	
	public static Reference instance() {
		if(ref == null) {
			ref = new Reference();
		}
		return ref;
	}
	
	public Viewport getCurrentViewport() {
		return currentViewport.get();
	}
	
	public void setCurrentViewport(Viewport viewport) {
		currentViewport = new WeakReference<Viewport>(viewport);
	}
}
