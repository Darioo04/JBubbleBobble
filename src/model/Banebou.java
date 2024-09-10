package model;

import java.util.Random;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Banebou extends Enemy {
	
	private static final int JUMP_STRENGHT = 4*GameConstants.SCALE;
	private int targetY;
	private boolean isJumping=false;
	private boolean inAir=false;
	private int fallingSpeed = 0;
	private Random random;
	private int velocityX;
	private int velocityY;
	
	public Banebou(int x, int y) {
		super(x,y);
		setPath("/sprites/banebou/");
		setDirection(Direction.RIGHT);
		setNumIdleSprites(1);
		setNumRunningSprites(0);
		setNumJumpingSprites(3);
		setSpeed(9);
		scoreWhenKilled = 500;
		random = new Random();
		generateRandomDirection();
	}
	
	@Override
	public void update() {
		super.update();
		collisionChecker.checkTileCollision(this);
		if (!isDead() && !isInBubble() && !isFrozen()) {
			x += velocityX;
			y += velocityY;
			
			if (x <= 2*GameConstants.TILE_SIZE || x >= GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE) {
				velocityX = -velocityX;
			}
			if (y <= GameConstants.TILE_SIZE || y >= GameConstants.SCREEN_HEIGHT - 2*GameConstants.TILE_SIZE) {
				velocityY = -velocityY;
			}
		}
//		if (!isDead() && !isInBubble() && !isFrozen()) {
//			if (Math.random() < 0.03) {
//				randomizeDirection();
//			}
//			else if (!collisionDown && y + Math.abs(speed) <  GameConstants.SCREEN_HEIGHT - 2 * GameConstants.TILE_SIZE) {
//	                y += Math.abs(speed);
//			}
//			else {
//				switch (direction) {
//					case RIGHT -> {
//						if(x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed && !getCollisionRight()) {
//							x += 25;
//							y-=70;
//						}else {
//							randomizeDirection();
//						}
//					}
//					
//					case LEFT -> {
//						if(x > 2*GameConstants.TILE_SIZE + speed && !getCollisionLeft()) {
//		                    x -= 25;
//		                    y-=70;
//		                }else {
//							randomizeDirection();
//						}
//					 
//					}
//					
//					default ->{}
//				}
//			}
//		}
		
		
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
	private void generateRandomDirection() {
        int angle = random.nextInt(360); // Angolo casuale tra 0 e 360 gradi

        // Scomposizione in componenti intere di velocità, arrotondando
        velocityX = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        velocityY = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
    }
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
		
        if (randomNumber <= 0.50) {
        	setDirection(Direction.LEFT);
        }
        else{
            setDirection(Direction.RIGHT);
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
}
