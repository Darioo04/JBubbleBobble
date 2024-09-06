package model;

@SuppressWarnings("deprecation")

public class SuperDrunk extends Enemy {
	//boos finale
	
	public SuperDrunk(int x,int y) {
		super(x,y);
		setPath("/sprites/super drunk/");
		setDirection(Direction.RIGHT);
		setSpeed(4);
	}
	
	@Override
	public void update() {
		if (Math.random() < 0.03) {
            randomizeDirection();
        }
		int speed = getSpeed();
		switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 2*GameConstants.TILE_SIZE - speed) {
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
	            if (y < GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE - speed) {
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
