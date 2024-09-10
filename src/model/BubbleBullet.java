package model;

import java.awt.Rectangle;

@SuppressWarnings("deprecation")

public class BubbleBullet extends Bubble {
	
	private static int bulletDistance = GameConstants.BUBBLE_X_DISTANCE;
	private static int bulletSpeed = GameConstants.BUBBLE_X_SPEED;
	
	
	public BubbleBullet(int x, int y, Direction direction) {
		super(x,y);
		setDirection(direction);
		setPath("default-");
		setNumSprites(1);
		setExpanded(false);
        setTargetX((direction == Direction.RIGHT) ? getSpawnX() + bulletDistance : getSpawnX() - bulletDistance);
	}
	
	@Override
	public void update() {
		collisionLeft = false;
		collisionRight = false;
		getCollisionChecker().checkTileCollision(this);
		Direction direction = getDirection();
		int targetX = getTargetX();
		if (y > GameConstants.TILE_SIZE && !isExploded()) {
			if(!isExpanded()) {
				switch (direction){
					case RIGHT -> {
						if(x != targetX)
							x += bulletSpeed;
					}
					
					case LEFT -> {
						if(x!= targetX)
							x -= bulletSpeed;
					}
					
					default -> throw new IllegalArgumentException("Unexpected value: " + direction);
				}
					
				if (targetX == x || getCollisionLeft() || getCollisionRight()) {
					setExpanded(true);
					setFloating(true);
					if (direction == Direction.RIGHT) {
						x -= (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2;
					}
					else {
						x += (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2;
					}
					y -= (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2;
					
					setHitboxWidth(GameConstants.BUBBLE_EXPANDED_SIZE - 2*GameConstants.SCALE);
			        setHitboxHeight(GameConstants.BUBBLE_EXPANDED_SIZE - 2*GameConstants.SCALE);
			        setHitbox(new Rectangle(x + getHitboxOffsetX(), y + getHitboxOffsetY(), getHitboxWidth(), getHitboxHeight()));
				}
					
			}
			else {
				y -= GameConstants.BUBBLE_FLOATING_SPEED;
			}
		}
		else if (isExploded()) {
			explosionTime++;
			if (explosionTime>=EXPLOSION_DELAY) {
				setCanBeDeleted(true);
			}
		}
			
		updateHitbox();
        setChanged();
        notifyObservers();
	}
	
	public static void increaseBulletDistance() {
		bulletDistance = GameConstants.POWERED_BUBBLE_X_DISTANCE;
	}
	
	public static void resetBulletDistance() {
		bulletDistance = GameConstants.BUBBLE_X_DISTANCE;
	}
	
	public static void increaseBulletSpeed() {
		bulletSpeed = GameConstants.POWERED_BUBBLE_X_SPEED;
	}
	
	public static void resetBulletSpeed() {
		bulletSpeed = GameConstants.BUBBLE_X_SPEED;
	}
	
	
}
