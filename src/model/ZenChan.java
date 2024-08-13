package model;

import controller.GameController;
import controller.LevelCreator;

@SuppressWarnings("deprecation")

public class ZenChan extends Enemy {
	
	private boolean isChasingPlayer = false;
	private Player player;
	private int targetY;
	
	public ZenChan(int x,int y) {
		super(x,y);
		setPath("/sprites/zen-chan/");
		setDirection(Math.random() > 0.5 ? Direction.RIGHT : Direction.LEFT);	 //prima direzione random
		speed = 5;
		player = Player.getInstance();
	}
	
	@Override
	public void update() {
		setDirectionToGo();
		setEnemyCollision();
		if (isChasingPlayer) {
			if(collisionDown) {
				isChasingPlayer = false;
			} else 
				y += speed;
		}
		else if (GameController.frames % 30 == 0 && Math.random() < 0.1 && player.getCollisionDown() && this.collisionDown && hasTilesAbove()) {
			if(player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE) > this.y) {
				speed = Math.abs(speed);
				isChasingPlayer = true;
			} else if (player.getY() - (GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE) < this.y) {
				speed = -speed;
				isChasingPlayer = true;
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
//		if (collisionDown && collisionLeft && collisionRight) {
//			y -= GameConstants.SCALE;
//		}
//		if (isGoingUp && !collisionDown && getHitboxY() > 2*GameConstants.TILE_SIZE) {
//			y -= speed;
//		}
//		else if(!collisionDown && player.getY() > y) {
//	            y +=speed; // Aggiorna la posizione verticale
//		}
//		
//		else if (collisionDown) {
//			isGoingUp = false;
//			if (player.getY() > y && canGoUp() && getHitboxY() > 2*GameConstants.TILE_SIZE) {
//				y -= speed;
//				isGoingUp = true;
//			} else {
//				switch (direction) {
//				case RIGHT -> {
//					if(!collisionRight) {
//						x += speed;
//					}
//				}
//				
//				case LEFT -> {
//					if(!collisionLeft) {
//	                    x -= speed;
//	                }
//				}
//				
//				default ->
//				throw new IllegalArgumentException("Unexpected value: " + direction);
//				}
//			}
//		}
//		else {
//			y -= speed;
//		}
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
