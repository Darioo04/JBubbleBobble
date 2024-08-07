package model;

@SuppressWarnings("deprecation")

public class Invader extends Enemy {
	private int speed = 3;
	
	public Invader(int x, int y) {
		super(x, y);
		this.setPath("/sprites/invader/");
		this.setDirection(Direction.RIGHT);
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public int getFallingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update() {
		if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
            randomizeDirection();
        }
		
		switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed) {
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
	            if (y < GameConstants.SCREEN_HEIGHT - 3*GameConstants.TILE_SIZE - speed) {
	                y += speed;
	            }
	            else {
					randomizeDirection();
				}
	        }
			
			case UP -> {
				if (y > 2*GameConstants.TILE_SIZE + speed) {
	                y -= speed;
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
}
