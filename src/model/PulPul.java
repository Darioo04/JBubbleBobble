package model;

@SuppressWarnings("deprecation")

public class PulPul extends Enemy {
	
	public PulPul(int x,int y) {
		super(x,y);
		setPath("/sprites/pulpul/");
		setDirection(Direction.RIGHT);
		setSpeed(3);
		setNumIdleSprites(1);
		setNumRunningSprites(3);
		setNumJumpingSprites(0);
	}
	
	@Override
	public void update() {
		super.update();
		collisionChecker.checkTileCollision(this);
		if (!isDead() && !isInBubble()) {
			if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
	            randomizeDirection();
	        }
		if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
            randomizeDirection();
        }
		int speed = getSpeed();
		switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed && !getCollisionRight()) {
					x += speed;
				}
				else {
					randomizeDirection();
				}
			}
			
			case LEFT -> {
				if (x > 2*GameConstants.TILE_SIZE + speed && !getCollisionLeft()) {
	                x -= speed;
	            }
				else {
					randomizeDirection();
				}
			}
			
			case DOWN -> {
	            if (y < GameConstants.SCREEN_HEIGHT - 3*GameConstants.TILE_SIZE - speed && !getCollisionDown()) {
	                y += speed;
	            }
	            else {
					randomizeDirection();
				}
	        }
			
			case UP -> {
				if (y > 2*GameConstants.TILE_SIZE + speed && !getCollisionUp()) {
	                y -= speed;
	            }
				else {
					randomizeDirection();
				}
	        }
		
		
			
			default ->{}
			}
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
