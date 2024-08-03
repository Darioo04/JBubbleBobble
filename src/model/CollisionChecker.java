package model;

import java.awt.Rectangle;

import controller.LevelCreator;

public class CollisionChecker {
	private static CollisionChecker instance;
	private char[][] levelFile;
	
	public static CollisionChecker getInstance() {
		if (instance==null) instance = new CollisionChecker();
		return instance;
	}
	
	private CollisionChecker() {
        
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
		
		if (entity instanceof Player) {
			if (levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1') {
				entity.setY(entity.getY() - (bottomY - bottomRow*GameConstants.TILE_SIZE));
			}
		}
		
		bottomRow = (bottomY + entity.getFallingSpeed()) / GameConstants.TILE_SIZE;
		entity.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
//		bottomRow = bottomY / GameConstants.TILE_SIZE;
		leftCol = (leftX - entity.getSpeed()) / GameConstants.TILE_SIZE;
		entity.setCollisionLeft(levelFile[bottomRow-1][leftCol] == '1' || levelFile[topRow][leftCol] == '1');
		
		rightCol = (rightX + entity.getSpeed()) / GameConstants.TILE_SIZE;
		entity.setCollisionRight(levelFile[bottomRow-1][rightCol] == '1' || levelFile[topRow][rightCol] == '1');
		
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
