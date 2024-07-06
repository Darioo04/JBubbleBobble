package model;

import model.UtilityClass.*;

public final class GameConstants {
	private GameConstants() {
		throw new UnsupportedOperationException("can't instantiate this class");		//THIS IS A UTILITY CLASS TO KEEP SOME GAME CONSTANTS
	}
	
	public static final int ORIGINAL_TILE_SIZE = 16;
	public static final int ORIGINAL_WIDTH = 256;
	public static final int ORIGINAL_HEIGHT = 224;
	public static double SCALE = UtilityClass.getScale();
	public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
}
