package model;

import java.awt.Rectangle;

import controller.LevelCreator;

public class CollisionChecker {
	
	private char[][] levelFile;
	
	public CollisionChecker() {
        
    }

	
	public void checkTileCollision(Entity entity) {
		
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle entityHitbox = entity.getHitbox();
		
		int leftX = entityHitbox.x;
		int rightX = leftX + entityHitbox.width;
		int topY = entityHitbox.y;
		int bottomY = topY + entityHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
//		char tile1, tile2;
		
		bottomRow = (bottomY + entity.getSpeed()) / GameConstants.TILE_SIZE;
//		tile1 = levelFile[bottomRow][leftCol];
//		tile2 = levelFile[bottomRow][rightCol];
		
		entity.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
	}
	
//	private boolean hasTileCollision(int row, int col) {		//controllo se il tile in quella posizione della mappa ha la collisione
//		char tileChar = levelFile[row][col];
//		return 
//			switch (tileChar) {
//				case '1' -> true;
//			
//				case ' ' -> false;
//			
//				case 'R' -> false;
//			
//				default -> throw new IllegalArgumentException("Unexpected value: " + tileChar);
//				};
//	}
}
