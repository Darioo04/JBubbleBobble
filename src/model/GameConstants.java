package model;


public final class GameConstants {
	private GameConstants() {
		throw new UnsupportedOperationException("can't instantiate this class");		//THIS IS A UTILITY CLASS TO KEEP SOME GAME CONSTANTS
	}
	
	public static final int ORIGINAL_TILE_SIZE = 16;
	public static final int ORIGINAL_WIDTH = 256;
	public static final int ORIGINAL_HEIGHT = 224;
	public static final int SCALE = 5;
	public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
	public static final int COLS = 15;
	public static final int ROWS = 14;
	public static final int SCREEN_WIDTH = TILE_SIZE * COLS;
	public static final int SCREEN_HEIGHT = TILE_SIZE * ROWS;
}
