package model;

import java.awt.Rectangle;
import java.util.Observable;

import view.BubbleView;

@SuppressWarnings("deprecation")

public class BubbleBullet extends Bubble {
	
	public BubbleBullet(int x, int y, Direction direction) {
		super(x,y);
		setDirection(direction);
        setTargetX((direction == Direction.RIGHT) ? getSpawnX() + GameConstants.BUBBLE_X_DISTANCE : getSpawnX() - GameConstants.BUBBLE_X_DISTANCE);
	}
	
	@Override
	public void update() {
		Direction direction = getDirection();
		int x = getX();
		int y = getY();
		int targetX = getTargetX();
		if(!isExpanded()) {
			switch (direction){
				case RIGHT -> {
					if(x != targetX)
						setX(x += GameConstants.BUBBLE_X_SPEED);
					if(x > GameConstants.SCREEN_WIDTH - 2*GameConstants.TILE_SIZE) {
						setX(GameConstants.SCREEN_WIDTH - 2*GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE);
						targetX = x;
					}
				}
				
				case LEFT -> {
					if(x!= targetX)
						setX(x - GameConstants.BUBBLE_X_SPEED);
					if(x < 2*GameConstants.TILE_SIZE) {
						setX(GameConstants.TILE_SIZE / 2 * 3);
						targetX = x;
					}
				}
				
				default -> throw new IllegalArgumentException("Unexpected value: " + direction);
			}
				
			if (targetX == x) {
				setExpanded(true);
				if (direction == Direction.RIGHT) {
					setX(x - (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2);
				}
				else {
					setX(x + (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2);
				}
				setY(y - (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2);
				
				setHitboxWidth(GameConstants.BUBBLE_EXPANDED_SIZE - 2*GameConstants.SCALE);
		        setHitboxHeight(GameConstants.BUBBLE_EXPANDED_SIZE - 2*GameConstants.SCALE);
		        setHitbox(new Rectangle(x + getHitboxOffsetX(), y + getHitboxOffsetY(), getHitboxWidth(), getHitboxHeight()));
			}
				
		}else {
			y-=GameConstants.BUBBLE_FLOATING_SPEED;
			setY(y);
		}
			
		updateHitbox();
        setChanged();
        notifyObservers();
	}
		  
}
