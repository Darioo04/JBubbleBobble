package model;

import java.awt.Rectangle;
import java.util.List;
import java.util.stream.Collectors;

import controller.GameController;
import controller.LevelCreator;

public class CollisionChecker {
	/*
	 * classe che si occupa di gestire le collisioni tra diversi elementi di gioco
	 */
	private static CollisionChecker instance;
	private char[][] levelFile;
	
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
		
		topRow = (topY - enemy.getSpeed()) / GameConstants.TILE_SIZE;
		if (topRow >= 0)
			enemy.setCollisionUp(levelFile[topRow][leftCol] == '1' || levelFile[topRow][rightCol] == '1');
		else
			enemy.setCollisionUp(true);
		
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
		bottomRow = bottomY / GameConstants.TILE_SIZE;
		food.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
	}
	
	public void checkTileCollision(PowerUp powerUp) {
		
		this.levelFile = LevelCreator.getInstance().getLevel();
		
		Rectangle powerUpHitbox = powerUp.getHitbox();
		
		int leftX = powerUpHitbox.x;
		int rightX = leftX + powerUpHitbox.width;
		int topY = powerUpHitbox.y;
		int bottomY = topY + powerUpHitbox.height;
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = bottomY / GameConstants.TILE_SIZE;
		powerUp.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
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
            		
            	} else if (!enemy.isInBubble() && !enemy.getBubbleExploded() && !player.getLostLife() && !player.isDead() && !player.isInvincible()) {
                	player.decreaseLives();
            	}
            }
            
		}
	}
	
	public void checkBubblePlayerCollision(List<Bubble> bubbles, Player player) {
        Rectangle playerHitbox = player.getHitbox();
        
        for (Bubble bubble : bubbles) {
        	Rectangle bubbleHitbox = bubble.getHitbox();
	        if (bubbleHitbox.intersects(playerHitbox) && !bubble.isExploded()){
	        	bubble.setExploded(true);
	        	bubble.setFloating(false);
	        	bubble.setHitbox(new Rectangle(0, 0, 1, 1));
	        	if (bubble instanceof BubbleBullet) {
	        		player.increaseBubbleBulletsPopped(); // incremento il numero di bolle proiettili esplose
	        	}
	        	else if (bubble instanceof ThunderBubble) {
	        		player.increaseThunderBubblesPopped();  // incremento il numero di ThunderBubble esplose
	        		GameController.getInstance().addObj( new Thunder(bubble.getX(), bubble.getY(), player.getDirection()) );
	        	}
	        	else if (bubble instanceof FireBubble) {
	        		player.increaseFireBubblesPopped();  // incremento il numero di FireBubble esplose
	        		GameController.getInstance().addObj( new Fire(bubble.getX(), bubble.getY()) );
	        	}
	        	else if (bubble instanceof ExtendBubble) {
	        		((ExtendBubble) bubble).deleteLetter(); 
	        	}
	        	else if (bubble instanceof WaterBubble) {
	        		GameController.getInstance().createWaterfall(bubble.getX(), bubble.getY(), bubble.getX() < GameConstants.SCREEN_WIDTH/2 ? Direction.RIGHT : Direction.LEFT);
	        	}
	        	else if (bubble instanceof SupremeBubble) {
	        		GameController.getInstance().addScore(100000);
	        		player.setInvicibility(true);
	        	}
	        }     
        }
        //fa esplodere tutte le bolle in contatto con la bolla appena fatta esplodere
        
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
	
	public void checkFoodPlayerCollision(Food food, Player player) {
		this.levelFile = LevelCreator.getInstance().getLevel();
        Rectangle foodHitbox = food.getHitbox();
        Rectangle playerHitbox = player.getHitbox();

        if (foodHitbox.intersects(playerHitbox)){
        	player.increaseFoodCollected();
        	food.setCollected(true);
        }
        
	}
	
	public void checkBubbleEnemyCollision(Bubble bubble, List<Enemy> enemyList) {
		Rectangle bubbleHitbox = bubble.getHitbox();
		enemyList.stream()
			.filter(enemy -> !(enemy instanceof SuperDrunk) && bubble instanceof BubbleBullet && bubbleHitbox.intersects(enemy.getHitbox()) && !bubble.isExpanded() && !enemy.isInBubble())
			.forEach(enemy -> {
				enemy.setInBubble(true);
				bubble.setCanBeDeleted(true);
			});
	}
	
	public void checkPlayerPowerUpCollision(Player player, PowerUp powerUp) {
		Rectangle playerHitbox = player.getHitbox();
		Rectangle powerUpHitbox = powerUp.getHitbox();
		
		if (playerHitbox.intersects(powerUpHitbox) && !powerUp.isInizialized()) {
			powerUp.inizializePoweredTime();
			powerUp.setCanBeDeleted(true);
		}
	}
	
	public void checkEntityWaterCollision(Entity e, List<Water> water) {
		Rectangle entityHitbox = e.getHitbox();
		if (e instanceof Enemy) {
			for (Water particleWater : water) {
				Rectangle waterHitbox = particleWater.getHitbox();
				if (entityHitbox.intersects(waterHitbox)) {
					((Enemy) e).setIsChasingPlayer(false);
					((Enemy) e).setBubbleExploded(true);
				}
			}
		}
	}
	
	public void checkPlayerLaserCollision(Player player, Laser laser) {
		Rectangle laserHitbox = laser.getHitbox();
		Rectangle playerHitbox = player.getHitbox();
		if (laserHitbox.intersects(playerHitbox) && !player.getLostLife() && !player.isDead() && !player.isInvincible()) {
			player.decreaseLives();
		}
	}
	
	public void checkPlayerFireBallCollision(Player player, FireBall fireBall) {
		Rectangle fireBallHitbox = fireBall.getHitbox();
		Rectangle playerHitbox = player.getHitbox();
		if (fireBallHitbox.intersects(playerHitbox) && !player.getLostLife() && !player.isDead() && !player.isInvincible()) {
			player.decreaseLives();
		}
	}
	
	public void checkThunderEnemyCollision(Thunder thunder, List<Enemy> enemies) {
		Rectangle thunderHitbox = thunder.getHitbox();
		
		//comportamento del fulmine per i nemici diversi dal SuperDrunk
		enemies.stream()
			.filter( enemy -> !(enemy instanceof SuperDrunk ) && enemy.getHitbox().intersects(thunderHitbox))
			.forEach(enemy -> enemy.setDead(true));
		
		//comportamento del fulmine per il superDrunk
		enemies.stream()
			.filter( enemy -> enemy instanceof SuperDrunk )
			.map(enemy -> (SuperDrunk)enemy)
			.forEach(enemy -> {
				enemy.decreaseLives();
				thunder.setCanBeDeleted(true);
			});
	}
	
	public void checkFireEnemyCollision(Fire fire, List<Enemy> enemies) {
		Rectangle fireHitbox = fire.getHitbox();
		//comportamento del fuoco per i nemici diversi dal SuperDrunk
		enemies.stream()
			.filter(enemy -> !(enemy instanceof SuperDrunk) && enemy.getHitbox().intersects(fireHitbox))
			.forEach(enemy -> enemy.setDead(true));
	}
}
