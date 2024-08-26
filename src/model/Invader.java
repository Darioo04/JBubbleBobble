package model;

@SuppressWarnings("deprecation")

public class Invader extends Enemy {
	//sparano verso sotto
	
	public Invader(int x, int y) {
		super(x, y);
		this.setPath("/sprites/invader/");
		this.setDirection(Direction.RIGHT);
		setSpeed(6);
		setNumIdleSprites(2);
		setNumRunningSprites(0);
		setNumJumpingSprites(0);
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public int getFallingSpeed() {
		return 0;
	}

	@Override
	public void update() {
		super.update();
		collisionChecker.checkTileCollision(this);
		if (!isDead() && !isInBubble()) {
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
				
				default ->{}
			}
		}
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
        setDirection((randomNumber<=0.5) ? Direction.LEFT : Direction.RIGHT);
	}
}
