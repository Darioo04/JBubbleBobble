package model;

@SuppressWarnings("deprecation")

public class Hidegons extends Enemy{
	// spara palle di fuoco, si muove solo a destra e a sinistra
	public Hidegons(int x,int y) {
		super(x,y);
		setDirection(Direction.RIGHT);
		setPath("/sprites/hidegons/");
		setSpeed(6);
	}
	
	@Override
	public void update() {
		if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
            randomizeDirection();
        }
		int speed = getSpeed();
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
	}
}
