package model;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Invader extends Enemy {
	//sparano verso sotto
	private int shootingCounter;
	private final static int SHOOTING_DELAY = 90;
	
	public Invader(int x, int y) {
		super(x, y);
		this.setPath("/sprites/invader/");
		this.setDirection(Direction.RIGHT);
		shootingCounter = 1;
		setMoving(false);
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
		if (!isDead() && !isInBubble() && !isFrozen() && !getBubbleExploded()) {
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
			shootingCounter++;
			if (shootingCounter >= SHOOTING_DELAY && !isDead() && !isInBubble()) { //l'invader spara ogni 1,5 secondi
				GameController.getInstance().addObj( new Laser(getX(),getY()+10) );
				shootingCounter = 1;
			}
		}
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
//	private boolean isPlayerBelow() {
//		return player.getY()>getY() && player.getX()==getX();
//	}
	
	private void randomizeDirection() {
        setDirection((Math.random()<=0.5) ? Direction.LEFT : Direction.RIGHT);
	}
}
