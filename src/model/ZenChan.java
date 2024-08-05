package model;

@SuppressWarnings("deprecation")

public class ZenChan extends Enemy {
	
	private boolean isGoingUp = false;
	
	public ZenChan(int x,int y) {
		super(x,y);
		setPath("/sprites/zen-chan/");
		setDirection(Direction.RIGHT);
		setSpeed(5);
	}
	
	@Override
	public void update() {
		this.setDirectionToGo();
		this.setEnemyCollision();
		if (isGoingUp && !collisionDown) {
			y -= speed;
		}
		else if(!collisionDown && player.getY() > y) {
	            y +=speed; // Aggiorna la posizione verticale
		}
		
		else if (collisionDown) {
			isGoingUp = false;
			if (player.getY() > y && canGoUp() && getHitboxY() > 2*GameConstants.TILE_SIZE) {
				y += speed;
				isGoingUp = true;
			} else {
				switch (direction) {
				case RIGHT -> {
					if(!collisionRight) {
						x += speed;
					}
				}
				
				case LEFT -> {
					if(!collisionLeft) {
	                    x -= speed;
	                }
				}
				
				default ->
				throw new IllegalArgumentException("Unexpected value: " + direction);
				}
			}
		}
		else {
			y -= speed;
		}
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
	private boolean canGoUp() {
		return Math.random() <= 0.01;
	}
	
	private void setDirectionToGo() {
		if (collisionLeft) {
			setDirection(Direction.RIGHT);
		}
		else if (collisionRight) {
            setDirection(Direction.LEFT);
        }
	}
	
	private void setEnemyCollision() {
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
		collisionChecker.checkTileCollision(this);
	}
	
//	private void randomizeDirection() {
//		double randomNumber = Math.random();
//		
//        if (randomNumber <= 0.25) {
//        	setDirection(Direction.LEFT);
//        }
//        else if (randomNumber <= 0.5) {
//            setDirection(Direction.RIGHT);
//        }
//        else if (randomNumber <= 0.75) {
//            setDirection(Direction.DOWN);
//        }
//        else {
//            setDirection(Direction.UP);
//        }
//	}
}
