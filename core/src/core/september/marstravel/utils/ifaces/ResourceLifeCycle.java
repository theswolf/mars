package core.september.marstravel.utils.ifaces;

import com.badlogic.gdx.Application;

public interface ResourceLifeCycle{

	public void create ();

	//public void resize (int width, int height);

	/** Called when the {@link Application} should render itself. */
	//public void render ();
	//public void pause ();
	//public void resume ();

	public void dispose ();
}
