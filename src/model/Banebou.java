package model;

import java.util.Random;

@SuppressWarnings("deprecation")

public class Banebou extends Enemy {
	
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
}
