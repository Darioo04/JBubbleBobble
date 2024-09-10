package model;

import java.util.Random;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Hidegons extends Enemy{
	private int targetY;
	private int frame = 0;
	
	// spara palle di fuoco, si muove solo a destra e a sinistra
	public Hidegons(int x,int y) {
		super(x,y);
		setDirection(Direction.RIGHT);
		setPath("/sprites/hidegons/");
		setSpeed(7);
		scoreWhenKilled = 300;
		setNumIdleSprites(1);
		setNumRunningSprites(3);
	}
	@Override
	public void update() {
		super.update();
		setDirectionToGo();
		if (hitbox.y + hitboxHeight >= GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE/4) {
			y = GameConstants.TILE_SIZE;
			updateHitbox();
		}
		setEnemyCollision();
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
				if (frame % 20 == 0 && Math.random() < 0.3 && this.collisionDown && hasTilesAbove()) {
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
			shot();
		}
		updateHitbox();
		setChanged();
        notifyObservers();
	}
		
	private void setDirectionToGo() {
		if (collisionLeft) {
			setDirection(Direction.RIGHT);
		}
		else if (collisionRight) {
            setDirection(Direction.LEFT);
        }
	}
	
	private void shot() {
		if (isPlayerForward()) {
			GameController.getInstance().addObj( new FireBall(getX(),getY(),direction) );
		}
		
	}
	
	private boolean isPlayerForward() {
		return switch (direction) {
			case LEFT -> getX()>player.getX() && getY()==player.getY();
			
			case RIGHT -> getX()<player.getX() && getY()==player.getY();

			default -> false;
			
		};
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
	
	private void setEnemyCollision() {
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
		collisionChecker.checkTileCollision(this);
	}
}
