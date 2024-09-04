package model;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Hidegons extends Enemy{
	// spara palle di fuoco, si muove solo a destra e a sinistra
	private FireBall fireBall;
	public Hidegons(int x,int y) {
		super(x,y);
		setDirection(Direction.RIGHT);
		setPath("/sprites/hidegons/");
		setSpeed(3);
		setNumIdleSprites(1);
		setNumRunningSprites(3);
	}
	@Override
	public void update() {
		super.update();
		collisionChecker.checkTileCollision(this);
		int speed = getSpeed();
		if (!isDead() && !isInBubble() && !isFrozen()) {
			if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
				randomizeDirection();
			}
			else if (!collisionDown && y + Math.abs(speed) <  GameConstants.SCREEN_HEIGHT - 2 * GameConstants.TILE_SIZE) {
	                y += Math.abs(speed);}
			
			else {
				switch (direction) {
					case RIGHT -> {
						if(x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed && !getCollisionRight()) {
							x += speed;
						}else {
							randomizeDirection();
						}
					}
					
					case LEFT -> {
						if(x > 2*GameConstants.TILE_SIZE + speed && !getCollisionLeft()) {
		                    x -= speed;
		                }else {
							randomizeDirection();
						}
					 
					}
					case UP -> { if (y > 2*GameConstants.TILE_SIZE + speed && !getCollisionUp() && hasTilesAbove()) {
							y-=100;				
						}
					}
					default ->{}
				}
			}
		}
		shot();
		updateHitbox();
		
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
		
        if (randomNumber <= 0.33) {
        	setDirection(Direction.LEFT);
        }
        else if (randomNumber<=0.66) {
            setDirection(Direction.RIGHT);
        }
        else {
        	setDirection(Direction.UP);
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
//		switch (direction) {
//			case RIGHT -> {
//				int x = getX();
//				while (!getCollisionRight()) {
//					x++;
//					setHitboxX(x);
//					collisionChecker.checkTileCollision(this);
//					
//				}
//				setHitboxX(getX());
//				int diff = x - getX();
//				return ( getX()>player.getX() && getX()+diff<player.getX() ) && getY()==player.getY();
//			}
//			
//			case LEFT -> {
//				int x = getX();
//				while (!getCollisionLeft()) {
//					x--;
//					setHitboxX(x);
//					collisionChecker.checkTileCollision(this);
//					
//				}
//				setHitboxX(getX());
//				int diff = x - getX();
//				return ( getX()<player.getX() && getX()+diff>player.getX() ) && getY()==player.getY();
//			}
//			
//			default -> {
//				
//			}
//		
//		}
//		return false;
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
}
