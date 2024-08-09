package model;

import java.awt.Rectangle;
import java.util.ArrayList;

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
		
		leftCol = (leftX - player.getSpeed() - GameConstants.SCALE) / GameConstants.TILE_SIZE;
		player.setCollisionLeft(levelFile[bottomRow][leftCol] == '1' || levelFile[topRow][leftCol] == '1');
		
		rightCol = (rightX + player.getSpeed() + GameConstants.SCALE) / GameConstants.TILE_SIZE;
		player.setCollisionRight(levelFile[bottomRow][rightCol] == '1' || levelFile[topRow][rightCol] == '1');
		
		leftCol = leftX / GameConstants.TILE_SIZE;
		rightCol = rightX / GameConstants.TILE_SIZE;
		bottomRow = (bottomY + player.getFallingSpeed() + 1) / GameConstants.TILE_SIZE;
		player.setCollisionDown(levelFile[bottomRow][leftCol] == '1' || levelFile[bottomRow][rightCol] == '1');
		
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
	
	public void checkPlayerEnemeyCollision(Player player, ArrayList<Enemy> enemyList) {
		this.levelFile = LevelCreator.getInstance().getLevel();
		Rectangle playerHitbox = player.getHitbox();
		
		for (Enemy enemy : enemyList) {
				Rectangle enemyHitbox = enemy.getHitbox();
            
            if (playerHitbox.intersects(enemyHitbox)) {
            	player.decreaseLives();
            }
		}
	}
	
	public boolean checkBubblePlayerCollision(BubbleBullet bubbleBullet, Player player) {
		this.levelFile = LevelCreator.getInstance().getLevel();
        Rectangle bubbleHitbox = bubbleBullet.getHitbox();
        Rectangle playerHitbox = player.getHitbox();
            
        if (bubbleHitbox.intersects(playerHitbox)) {
                return true;
        }
        return false;
	}
	
	public void checkBubbleEnemyCollision(BubbleBullet bubbleBullet, ArrayList<Enemy> enemyList) {
        Rectangle bubbleHitbox = bubbleBullet.getHitbox();
        
        for (Enemy enemy : enemyList) {
                Rectangle enemyHitbox = enemy.getHitbox();
            
            if (bubbleHitbox.intersects(enemyHitbox)) {
                //da implementare
            }
        }
	}
}
