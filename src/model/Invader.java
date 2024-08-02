package model;

public class Invader extends Enemy {
	protected String filePath;
	private String direction = "right";
	private int speed = 3;
	
	public Invader(int x, int y) {
		super(x, y, "Invader");
		this.setPath("/sprites/invader/");
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
		
		switch (direction) {
			case "right" -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed) {
					x += speed;
				}
				else {
					randomizeDirection();
				}
			}
			
			case "left" -> {
				if (x > 2*GameConstants.TILE_SIZE + speed) {
	                x -= speed;
	            }
				else {
					randomizeDirection();
				}
			}
			
			case "down" -> {
	            if (y < GameConstants.SCREEN_HEIGHT - 3*GameConstants.TILE_SIZE - speed) {
	                y += speed;
	            }
	            else {
					randomizeDirection();
				}
	        }
			
			case "up" -> {
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
        	direction = "left";
        }
        else if (randomNumber <= 0.5) {
            direction = "right";
        }
        else if (randomNumber <= 0.75) {
            direction = "down";
        }
        else {
            direction = "up";
        }
	}
}
