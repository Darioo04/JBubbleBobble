package model;

import java.util.Random;

import controller.GameController;
import controller.LevelCreator;

@SuppressWarnings("deprecation")

public class ZenChan extends Enemy {
	
	private int targetY;
	private int frame = 0;
	
	public ZenChan(int x,int y) {
		super(x,y);
		scoreWhenKilled = 100;
		setPath("/sprites/zen-chan/");
		setDirection(Math.random() > 0.5 ? Direction.RIGHT : Direction.LEFT);	 //prima direzione random
		setSpeed(5);
		setNumIdleSprites(1);
		setNumRunningSprites(2);
		setNumJumpingSprites(0);
		setIsChasingPlayer(false);
	}
	
	@Override
	public void update() {
		super.update();
		setDirectionToGo();
		setEnemyCollision();
		if (hitbox.y + hitboxHeight - 1 >= GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE) collisionDown = true;
//		if (!isDead() && !isFrozen()) {	
//			if (isChasingPlayer()) {
//				if(Math.abs(targetY - y) <= 9) {
//					setIsChasingPlayer(false);
//				} else 
//					y += speed;
//			}
//			else if (!isInBubble()) {
//				if (GameController.frames % 30 == 0 && Math.random() < 0.1 && this.collisionDown && hasTilesAbove()) {
//					if(player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE) > this.y) {
//						speed = Math.abs(speed);
//					} else if (player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE) < this.y) {
//						speed = -speed;
//					}
//					setIsChasingPlayer(true);
//					targetY = player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE);
//					y += speed;
//				}
//				else if(!collisionDown) {
//					y += Math.abs(speed);
//				} else {
//					switch (direction) {
//					case RIGHT -> {
//						if(!collisionRight) {
//							x += speed;
//						}else {
//							direction = Direction.LEFT;
//						}
//					}
//					
//					case LEFT -> {
//						if(!collisionLeft) {
//		                    x -= speed;
//		                }else {
//							direction = Direction.RIGHT;
//						}
//					}
//			
//	
//					
//					default ->
//					throw new IllegalArgumentException("Unexpected value: " + direction);
//					}
//				}
//			}
//		}
		if (!isDead() && !isFrozen() && !isInBubble() && !getBubbleExploded()) {	
			if (isChasingPlayer()) {
				if(Math.abs(targetY - y) <= 9 && !collisionDown) {
					setIsChasingPlayer(false);
					setDirection(Math.random() > 0.5 ? Direction.RIGHT : Direction.LEFT);
				} else 
					y -= speed;
				if (y <= GameConstants.TILE_SIZE) {
					setIsChasingPlayer(false);
					y = GameConstants.TILE_SIZE;
					setDirection(Math.random() > 0.5 ? Direction.RIGHT : Direction.LEFT);
				}
			}
			else {
				frame++;
				if (frame % 30 == 0 && Math.random() < 0.3 && this.collisionDown && hasTilesAbove()) {
					setIsChasingPlayer(true);
					int tilesAbove = y / GameConstants.TILE_SIZE - 1;
					targetY = (new Random().nextInt(tilesAbove) + 1) * GameConstants.TILE_SIZE;
					y -= speed;
				}
				else if(!collisionDown) {
					y += speed;
				} else {
					switch (direction) {
					case RIGHT -> {
						if(!collisionRight) {
							x += speed;
						}else {
							direction = Direction.LEFT;
						}
					}
					
					case LEFT -> {
						if(!collisionLeft) {
		                    x -= speed;
		                }else {
							direction = Direction.RIGHT;
						}
					}
			
	
					
					default ->
					throw new IllegalArgumentException("Unexpected value: " + direction);
					}
				}
				if (frame==60) frame = 0;
			}
		}
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
	private boolean isNextStepOnTile(boolean right) {
		if (right == true) {
			x += this.speed;
			collisionChecker.checkTileCollision(this);
			x -= this.speed;
			if (!collisionDown) return false;
			else return true;
		} else {
			x -= this.speed;
            collisionChecker.checkTileCollision(this);
            x += this.speed;
            if (!collisionDown) return false;
            else return true;
		}
	}
	
	private boolean hasTilesAbove() {
		int row = y / GameConstants.TILE_SIZE;
		int col = x / GameConstants.TILE_SIZE;
		
        for (int i = row - 1; i >= 2; i--) {
        	if (levelFile[i][col] == '1') {
        		return true;
        	}
        }
        return false;
	}
	
	private boolean canGoUp() {
		return Math.random() <= 0.01;
	}
	
	private void setDirectionToGo() {
		if (collisionLeft) {
			setDirection(Direction.RIGHT);
		}
		else if (collisionRight) {
            setDirection(Direction.LEFT);
        }
	}
	
	private void setEnemyCollision() {
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
		collisionChecker.checkTileCollision(this);
	}
	
}
