package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY) / GameConstants.TILE_SIZE;
		food.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
	}
	
	public void checkTileCollision(Fire fire) {
		
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle fireHitbox = fire.getHitbox();
		
		int leftX = fireHitbox.x;
		int rightX = leftX + fireHitbox.width;
		int topY = fireHitbox.y;
		int bottomY = topY + fireHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY) / GameConstants.TILE_SIZE;
		fire.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
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
            		
            	} else if (!enemy.isInBubble() && !enemy.getBubbleExploded() && !player.isDead()) {
                	player.decreaseLives();
            	}
            }
            
		}
	}
	
	public void checkBubblePlayerCollision(List<Bubble> bubbles, Player player) {
        Rectangle playerHitbox = player.getHitbox();
        
//        System.out.println("xb: " + bubbleHitbox.getX() + "   yb: " + bubbleHitbox.getY());
//        System.out.println("x: " + playerHitbox.getX() + "   y: " + playerHitbox.getY());
        for (Bubble bubble : bubbles) {
        	Rectangle bubbleHitbox = bubble.getHitbox();
        	int leftX = bubbleHitbox.x;
    		int rightX = leftX + bubbleHitbox.width;
	        if (bubbleHitbox.intersects(playerHitbox)){
	        	bubble.setExploded(true);
	        	bubble.setFloating(false);
	        	bubble.setHitbox(new Rectangle(0, 0, 1, 1));
	//        	System.out.println("collision");
	        	if (bubble instanceof BubbleBullet) {
	        		player.increaseBubbleBulletsPopped();
	        	}
	        	else if (bubble instanceof ThunderBubble) {
	        		player.increaseLightningBubblesPopped();
	        		GameController.getInstance().addObj( new Thunder(bubble.getX(), bubble.getY(), player.getDirection()) );
	        	}
	        	else if (bubble instanceof FireBubble) {
	        		player.increaseFireBubblesPopped();
	        		GameController.getInstance().addObj( new Fire(bubble.getX(), bubble.getY()) );
	        	}
	        	else if (bubble instanceof ExtendBubble) {
	        		((ExtendBubble) bubble).deleteLetter();
	        	}
	        	else if (bubble instanceof WaterBubble) {
	        		GameController.getInstance().addObj(new Water(bubble.getX(), bubble.getY()));
	        	}
	        }     
        }
        for (Bubble bubble : bubbles.stream().filter(b -> b.isExploded()).collect(Collectors.toList())) {
	        Rectangle bubbleHitbox = bubble.getHitbox();
	        bubbles.stream()
	                .filter(b -> b.getHitbox().intersects(bubbleHitbox))
	                .forEach(nearBubble -> {
	                	nearBubble.setFloating(false);
	                	nearBubble.setExploded(true);
	                });
	    }
	}
	
	public boolean checkFoodPlayerCollision(Food food, Player player) {
		this.levelFile = LevelCreator.getInstance().getLevel();
        Rectangle foodHitbox = food.getHitbox();
        Rectangle playerHitbox = player.getHitbox();

        if (foodHitbox.intersects(playerHitbox)){
        	player.increaseFoodCollected();
        	return true;
        }
        return false;
	}
	
	public void checkBubbleEnemyCollision(Bubble bubble, List<Enemy> enemyList) {
		Rectangle bubbleHitbox = bubble.getHitbox();
		for (Enemy enemy : enemyList) {
			Rectangle enemyHitbox = enemy.getHitbox();
          
			if (bubble instanceof BubbleBullet && bubbleHitbox.intersects(enemyHitbox) && !bubble.isExpanded() && !enemy.isInBubble()) {
				//da implementare
				enemy.setInBubble(true);
				GameController.getInstance().removeBubble(bubble);
			}
		}
	}
	
//	public void checkBubbleBubbleCollision(List<Bubble> bubbles,Player player) {
//		Rectangle playerHitbox = player.getHitbox();
//		for (Bubble bubble : bubbles) {
//			Rectangle bubbleHitbox = bubble.getHitbox();
//			if (playerHitbox.intersects(bubbleHitbox)) {
//				bubble.setExploded(true);
//	        	bubble.setFloating(false);
//	        	bubble.setHitbox(new Rectangle(0, 0, 1, 1));
//				
//			}
//		}
//		
////			if (bubbleHitbox1.intersects(bubbleHitbox2)) {
////				// crea un effetto rimbalzo tra le bolle
////			}
//	}
	
	public void checkPlayerPowerUpCollision(Player player, PowerUp powerUp) {
		Rectangle playerHitbox = player.getHitbox();
		Rectangle powerUpHitbox = powerUp.getHitbox();
		
		if (playerHitbox.intersects(powerUpHitbox)) {
			powerUp.inizializePoweredTime();
			powerUp.setCanBeDeleted(true);
		}
	}
	
	public void checkTileCollisionUp(Enemy enemy) {
		
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
		
		// Collisione verso sinistra
		leftCol = (leftX - enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionLeft(levelFile[bottomRow][leftCol] == '1' || levelFile[topRow][leftCol] == '1');
		
		// Collisione verso destra
		rightCol = (rightX + enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionRight(levelFile[bottomRow][rightCol] == '1' || levelFile[topRow][rightCol] == '1');
		
		// Collisione verso il basso
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY + enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');

		// **Collisione verso l'alto**
		topRow = (topY - enemy.getSpeed()) / GameConstants.TILE_SIZE;
		enemy.setCollisionUp(levelFile[topRow][leftCol] == '1' || levelFile[topRow][rightCol] == '1');
	}
	
}
