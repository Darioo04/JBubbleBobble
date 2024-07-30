package model;

import java.awt.Rectangle;

import controller.LevelCreator;

public class CollisionChecker {
	
	private char[][] levelFile;
	
	public CollisionChecker() {
        this.levelFile = LevelCreator.getInstance().getLevel();
    }

	
	public void checkTileCollision(Entity entity) {
		
		Rectangle entityHitbox = entity.getHitbox();
		
		int leftX = entityHitbox.x / GameConstants.TILE_SIZE;
		int rightX = leftX + entityHitbox.width / GameConstants.TILE_SIZE;
		int topY = entityHitbox.y / GameConstants.TILE_SIZE;
		int bottomY = topY + entityHitbox.height / GameConstants.TILE_SIZE;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
//		char tile1, tile2;
		
		bottomRow = (bottomY + entity.getSpeed()) / GameConstants.TILE_SIZE;
//		tile1 = levelFile[bottomRow][leftCol];
//		tile2 = levelFile[bottomRow][rightCol];
		
		entity.setCollisionDown(hasTileCollision(bottomRow, leftCol) || hasTileCollision(bottomRow, rightCol));
		
	}
	
	private boolean hasTileCollision(int row, int col) {		//controllo se il tile in quella posizione della mappa ha la collisione
		char tileChar = levelFile[row][col];
		switch (tileChar) {
		case '1' -> {
			return true;
		}
		case ' ' -> {
			return false;
		}
        case 'R' -> {
            return false;
        }
		
		default ->
		throw new IllegalArgumentException("Unexpected value: " + tileChar);
		}
	}
}
