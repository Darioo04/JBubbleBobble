package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import controller.GameController;
import controller.LevelCreator;

public class CollisionChecker {
	private static CollisionChecker instance;
	private char[][] levelFile;
	private Rectangle[][] tilesHitboxes;
	
	public static CollisionChecker getInstance() {
		if (instance==null) instance = new CollisionChecker();
		return instance;
	}
	
	private CollisionChecker() {
        
    }

	
	public void checkTileCollision(Player player) {
		
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle playerHitbox = player.getHitbox();
		
		int leftX = playerHitbox.x;
		int rightX = leftX + playerHitbox.width;
		int topY = playerHitbox.y;
		int bottomY = topY + playerHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;

		topY += GameConstants.SCALE;
		topRow = topY / GameConstants.TILE_SIZE;
		player.setCollisionUp((levelFile[topRow][leftCol] == '1' || levelFile[topRow][rightCol] == '1'));
			
		leftCol = (leftX - player.getSpeed() - GameConstants.SCALE) / GameConstants.TILE_SIZE;
		player.setCollisionLeft(levelFile[bottomRow][leftCol] == '1' || levelFile[topRow][leftCol] == '1');
		
		rightCol = (rightX + player.getSpeed() + 2*GameConstants.SCALE) / GameConstants.TILE_SIZE;
		player.setCollisionRight(levelFile[bottomRow][rightCol] == '1' || levelFile[topRow][rightCol] == '1');
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY + player.getFallingSpeed() + 1) / GameConstants.TILE_SIZE;
		if (bottomRow < GameConstants.ROWS) player.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
	}
	
	public void checkTileCollision(Enemy enemy) {
	
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle entityHitbox = enemy.getHitbox();
		
		int leftX = entityHitbox.x;
		int rightX = leftX + entityHitbox.width;
		int topY = entityHitbox.y;
		int bottomY = topY + entityHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		leftCol = (leftX - enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionLeft(levelFile[bottomRow][leftCol] == '1' || levelFile[topRow][leftCol] == '1');
		
		rightCol = (rightX + enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionRight(levelFile[bottomRow][rightCol] == '1' || levelFile[topRow][rightCol] == '1');
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY + enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
	}
	
	public void checkTileCollision(Bubble bubble) {
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle bubbleHitbox = bubble.getHitbox();
		
		int leftX = bubbleHitbox.x;
		int rightX = leftX + bubbleHitbox.width;
		int topY = bubbleHitbox.y;
		int bottomY = topY + bubbleHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		leftCol = (leftX - GameConstants.BUBBLE_X_SPEED - GameConstants.SCALE) / GameConstants.TILE_SIZE;
		bubble.setCollisionLeft(levelFile[bottomRow][leftCol] == '1' || levelFile[topRow][leftCol] == '1');
		
		rightCol = (rightX + GameConstants.BUBBLE_X_SPEED + GameConstants.SCALE) / GameConstants.TILE_SIZE;
		bubble.setCollisionRight(levelFile[bottomRow][rightCol] == '1' || levelFile[topRow][rightCol] == '1');
		
//		leftCol = leftX / GameConstants.TILE_SIZE;
//		rightCol = rightX / GameConstants.TILE_SIZE;
//		bottomRow = (int)(bottomY + GameConstants.BUBBLE_FLOATING_SPEED + 1) / GameConstants.TILE_SIZE;
//		bubble.setCollisionUp(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
	}
	
	public void checkTileCollision(Food food) {
		
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle foodHitbox = food.getHitbox();
		
		int leftX = foodHitbox.x;
		int rightX = leftX + foodHitbox.width;
		int topY = foodHitbox.y;
		int bottomY = topY + foodHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY) / GameConstants.TILE_SIZE;
		food.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
	}
	
	public void checkPlayerEnemyCollision(Player player, List<Enemy> enemyList) {
		this.levelFile = LevelCreator.getInstance().getLevel();
		Rectangle playerHitbox = player.getHitbox();
		
		for (Enemy enemy : enemyList) {
			Rectangle enemyHitbox = enemy.getHitbox();
            
            if (playerHitbox.intersects(enemyHitbox)) {
            	if (enemy.isInBubble()) {
            		enemy.setBubbleExploded(true);
            		enemy.setInBubble(false);
            		
            	} else if (!enemy.isInBubble() && !enemy.getBubbleExploded()) {
                	player.decreaseLives();
            	}
            }
            
		}
	}
	
	public void checkBubblePlayerCollision(Bubble bubble, Player player) {
//		this.levelFile = LevelCreator.getInstance().getLevel();
        Rectangle bubbleHitbox = bubble.getHitbox();
        Rectangle playerHitbox = player.getHitbox();
        
//        System.out.println("xb: " + bubbleHitbox.getX() + "   yb: " + bubbleHitbox.getY());
//        System.out.println("x: " + playerHitbox.getX() + "   y: " + playerHitbox.getY());
        
        if (bubbleHitbox.intersects(playerHitbox)){
        	bubble.setExploded(true);
        	bubble.setFloating(false);
        	bubble.setHitbox(new Rectangle(0, 0, 1, 1));
//        	System.out.println("collision");
        	if (bubble instanceof BubbleBullet) {
        		player.increaseBubbleBulletsPopped();
        	}
        	else if (bubble instanceof LightningBubble) {
        		player.increaseLightningBubblesPopped();
        	}
        	else if (bubble instanceof FireBubble) {
        		player.increaseFireBubblesPopped();
        	}
        }
//        if (bubbleHitbox.intersects(playerHitbox)) {
//             return true;
//        }
//        return false;
	}
	
//	public boolean checkBubblePlayerCollisionBoolean(Bubble bubble, Player player) {
//		Rectangle bubbleHitbox = bubble.getHitbox();
//        Rectangle playerHitbox = player.getHitbox();
//        
//        return bubbleHitbox.intersects(playerHitbox);
//	}
	
	public boolean checkFoodPlayerCollision(Food food, Player player) {
		this.levelFile = LevelCreator.getInstance().getLevel();
        Rectangle foodHitbox = food.getHitbox();
        Rectangle playerHitbox = player.getHitbox();

        if (foodHitbox.intersects(playerHitbox)){
        	return true;
        }
        return false;
	}
	
	public void checkBubbleEnemyCollision(Bubble bubble, List<Enemy> enemyList) {
		Rectangle bubbleHitbox = bubble.getHitbox();
		for (Enemy enemy : enemyList) {
			Rectangle enemyHitbox = enemy.getHitbox();
          
			if (bubble instanceof BubbleBullet && bubbleHitbox.intersects(enemyHitbox) && !bubble.isExpanded()) {
				//da implementare
				enemy.setInBubble(true);
				GameController.getInstance().removeBubble(bubble);
			}
		}
	}
	
	public void checkBubbleBubbleCollision(List<Bubble> bubbles,Player player) {
		Rectangle playerHitbox = player.getHitbox();
		for (Bubble bubble : bubbles) {
			Rectangle bubbleHitbox = bubble.getHitbox();
			if (playerHitbox.intersects(bubbleHitbox)) {
				bubble.setExploded(true);
	        	bubble.setFloating(false);
	        	bubble.setHitbox(new Rectangle(0, 0, 1, 1));
				bubbles.stream().filter(b -> b.getHitbox().intersects(bubbleHitbox)).forEach(b -> { 
					b.setExploded(true);
					bubble.setFloating(false);
		        	bubble.setHitbox(new Rectangle(0, 0, 1, 1));
				});
			}
		}
		
//			if (bubbleHitbox1.intersects(bubbleHitbox2)) {
//				// crea un effetto rimbalzo tra le bolle
//			}
	}
	
}
