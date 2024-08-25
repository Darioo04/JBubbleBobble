package model;

import controller.GameController;
import controller.LevelCreator;

@SuppressWarnings("deprecation")

public class ZenChan extends Enemy {
	
	
	private Player player;
	private int targetY;
	
	public ZenChan(int x,int y) {
		super(x,y);
		setPath("/sprites/zen-chan/");
		setDirection(Math.random() > 0.5 ? Direction.RIGHT : Direction.LEFT);	 //prima direzione random
		setSpeed(5);
		setNumIdleSprites(1);
		setNumRunningSprites(2);
		setNumJumpingSprites(0);
		setIsChasingPlayer(false);
		player = Player.getInstance();
	}
	
	@Override
	public void update() {
		setDirectionToGo();
		setEnemyCollision();
		super.update();
		if (!isDead()) {	
			if (isChasingPlayer()) {
				if(collisionDown) {
					setIsChasingPlayer(false);
				} else 
					y += speed;
			}
			else if (!isInBubble()) {
				if (GameController.frames % 30 == 0 && Math.random() < 0.1 && player.getCollisionDown() && this.collisionDown && hasTilesAbove()) {
					if(player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE) > this.y) {
						speed = Math.abs(speed);
						setIsChasingPlayer(true);
					} else if (player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE) < this.y) {
						speed = -speed;
						setIsChasingPlayer(true);
					}
					y += speed;
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
	
//	private void randomizeDirection() {
//		double randomNumber = Math.random();
//		
//        if (randomNumber <= 0.25) {
//        	setDirection(Direction.LEFT);
//        }
//        else if (randomNumber <= 0.5) {
//            setDirection(Direction.RIGHT);
//        }
//        else if (randomNumber <= 0.75) {
//            setDirection(Direction.DOWN);
//        }
//        else {
//            setDirection(Direction.UP);
//        }
//	}
}
