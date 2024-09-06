package model;

import java.awt.Rectangle;

@SuppressWarnings("deprecation")

public class SuperDrunk extends Enemy {
	//boos finale
	
	public SuperDrunk(int x,int y) {
		super(x,y);
		setPath("/sprites/super drunk/");
		setDirection(Direction.RIGHT);
		setSpeed(8);
		setNumIdleSprites(1);
		setNumRunningSprites(3);
		setNumJumpingSprites(0);
		
		hitboxHeight = GameConstants.BOSS_SIZE - 2*GameConstants.SCALE;
		hitboxWidth = GameConstants.BOSS_SIZE - 2*GameConstants.SCALE;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));	
	}
	
	@Override
	public void update() {
		if (Math.random() < 0.03) {
            randomizeDirection();
        }
		int speed = getSpeed();
		switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 2*GameConstants.TILE_SIZE - speed - GameConstants.BOSS_SIZE) {
					x += speed;
				}
				else {
					randomizeDirection();
				}
			}
			
			case LEFT -> {
				if (x > 2*GameConstants.TILE_SIZE + speed) {
	                x -= speed;
	            }
				else {
					randomizeDirection();
				}
			}
			
			case DOWN -> {
	            if (y < GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE - speed - GameConstants.BOSS_SIZE) {
	                y += speed;
	            }
	            else {
					randomizeDirection();
				}
	        }
			
			case UP -> {
				if (y > GameConstants.TILE_SIZE + speed) {
	                y -= speed;
	            }
				else {
					randomizeDirection();
				}
	        }
			
			default ->{}
		}
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
}
