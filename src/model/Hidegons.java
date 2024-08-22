package model;

@SuppressWarnings("deprecation")

public class Hidegons extends Enemy{
	// spara palle di fuoco, si muove solo a destra e a sinistra
	private FireBall fireBall;
	public Hidegons(int x,int y) {
		super(x,y);
		setDirection(Direction.RIGHT);
		setPath("/sprites/hidegons/");
		setSpeed(6);
	}
	
	@Override
	public void update() {
		collisionChecker.checkTileCollision(this);
		
//		if (inAir) {
//			if (y + fallingSpeed > GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE){
//				y = GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE;
//			} else if(y + fallingSpeed < GameConstants.TILE_SIZE) {
//				y = GameConstants.TILE_SIZE + 1;
//                fallingSpeed = 5;
//			}else {
//				y += fallingSpeed;
//				fallingSpeed += GRAVITY;
//			}
//				
//			updateHitbox();
//			collisionChecker.checkTileCollision(this);
//			if (fallingSpeed > 0 && collisionDown) {
//				inAir = false;
//				fallingSpeed = 0;
//				isJumping = false;
//			}
//		}
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
			
			default ->{}
		}
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
        setDirection((randomNumber<=0.5) ? Direction.LEFT : Direction.RIGHT);
	}
	
	private void shot() {
		
	}
}
