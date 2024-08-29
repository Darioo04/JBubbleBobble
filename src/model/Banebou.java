package model;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Banebou extends Enemy {
	//si muovono saltando ad arco
	private static final int JUMP_STRENGHT = 4*GameConstants.SCALE;
	private int targetY;
	private boolean isJumping=false;
	private boolean inAir=false;
	private int fallingSpeed = 0;
	
	public Banebou(int x, int y) {
		super(x,y);
		setPath("/sprites/banebou/");
		setDirection(Direction.RIGHT);
		setNumIdleSprites(1);
		setNumRunningSprites(0);
		setNumJumpingSprites(3);
		setSpeed(4);
		
	}
	
	@Override
	public void update() {
		super.update();
		collisionChecker.checkTileCollision(this);
		if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
            randomizeDirection(); 
        }
		int speed = getSpeed();
		if(!getCollisionDown()) inAir = true;
		
		if (inAir) {
			if (y + fallingSpeed > GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE) {
				y = GameConstants.TILE_SIZE/2;
				fallingSpeed = 0;
				inAir = true;
			}
			else if(y + fallingSpeed < GameConstants.TILE_SIZE) {
				y = GameConstants.TILE_SIZE + 1;
                fallingSpeed = 5;
			} else {
				y += fallingSpeed;
				fallingSpeed += GRAVITY;
			}
		}
			switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed && getCollisionRight()) {
					x += speed;
					jump();
				}
				else {
					randomizeDirection();
				} 
			}
			
			case LEFT -> {
				if (x > 2*GameConstants.TILE_SIZE + speed && !getCollisionLeft()) {
	                x -= speed;
	                jump();
	            }
				else {
					randomizeDirection();
				}
			}
			case DOWN -> {
	            if (y < GameConstants.SCREEN_HEIGHT - 3*GameConstants.TILE_SIZE - speed && !getCollisionDown()) {
	                y -= speed;
	                jump();
	            }
	            else {
					randomizeDirection();
				}
	        }
			
			case UP -> {
				if (y > 2*GameConstants.TILE_SIZE + speed && !getCollisionUp()) {
	                y -= speed;
	                jump();
	            }
				else {
					randomizeDirection();
				}
	        }
			
			default ->{}
			}
		
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
		
        if (randomNumber <= 0.25) {
        	setDirection(Direction.LEFT);
        }
        else if (randomNumber <= 0.5) {
            setDirection(Direction.RIGHT);
        }
        else if (randomNumber <= 0.75) {
            setDirection(Direction.DOWN);
        }
        else {
            setDirection(Direction.UP);
        }
        
	}
	public void jump() {
		if( !inAir) {
			fallingSpeed = -JUMP_STRENGHT;
			inAir = true;
		}
	}
	
	
	
}
