package core.september.marstravel.utils;

public class Constants {

	
	public enum Resources {
		LOGO(new String[]{"logo"}),
		ROCKET(new String[]{"rocket"}),
		FIRE(buildStringNumber("fire/fire-",25)),
		BOOM(buildStringNumber("boom/boom-",12)),
		EARTH(new String[]{"planets/earth"}),
		MARS(new String[]{"planets/mars"}),
		MOON(new String[]{"planets/moon"});
		
		public String [] res;
		private static  String[] buildStringNumber(String prefix,int loop) {
			String[] ret = new String[loop];
			for (int x = 0; x< loop; x++) {
				ret[x] = prefix+x;
			}
			return ret;
		}
		private Resources(String[] res) {
			this.res = res;
		}
	}
	
	public final static String ATLAS="images/pack.atlas";
	public final static String MAP="map/skymap.tmx";
	
	public final static int GAME_HEIGHT = 480;
	public final static int GAME_WIDTH = 320;
	
	public final static int MAP_HEIGHT = 4000;
	public final static int MAP_WIDTH = 2000;
	
	
	
}
