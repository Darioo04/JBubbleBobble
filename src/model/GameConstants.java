package model;


public final class GameConstants {
	private GameConstants() {
		throw new UnsupportedOperationException("can't instantiate this class");		//THIS IS A UTILITY CLASS TO KEEP SOME GAME CONSTANTS
	}
	
	public static final int ORIGINAL_TILE_SIZE = 16;
	public static final int ORIGINAL_WIDTH = 256;
	public static final int ORIGINAL_HEIGHT = 224;
	public static final int SCALE = 3;
	public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
	public static final int PLAYER_SIZE = 13 * SCALE;
	public static final int ENEMY_SIZE = 14 * SCALE;
	public static final int COLS = 16;
	public static final int ROWS = 14;
	public static final int SCREEN_WIDTH = TILE_SIZE * COLS;
	public static final int SCREEN_HEIGHT = TILE_SIZE * ROWS;
	public static final int BUBBLE_SHOT_SIZE = 6 * SCALE;
	public static final int BUBBLE_EXPANDED_SIZE = 12 * SCALE;
	public static final int BUBBLE_X_SPEED = 3 * SCALE;
	public static final int BUBBLE_X_DISTANCE = 72 * SCALE;
	public static final double BUBBLE_FLOATING_SPEED = 0.5 * SCALE;
	public static final int ITEM_SIZE = 10 * SCALE;
	public static final double SPEED_MULTIPLIER = 1.5 * SCALE;
	public static final double SHOOT_MULTIPLIER = 1.5 * SCALE;
}
