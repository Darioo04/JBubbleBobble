package model;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Banebou extends Enemy {
	//si muovono saltando ad arco
	private static final int JUMP_STRENGHT = 4*GameConstants.SCALE;
	private int targetY;
	
	public Banebou(int x, int y) {
		super(x,y);
		setPath("/sprites/banebou/");
		setDirection(Direction.RIGHT);
		setNumIdleSprites(1);
		setNumRunningSprites(0);
		setNumJumpingSprites(3);
		setSpeed(4);
		
	}
	
	@Override
	public void update() {
		super.update();
		setEnemyCollision();
		setDirectionToGo();
		if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
            randomizeDirection(); 
        }
		int speed = getSpeed();
		if (getCollisionDown()) {
			switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed) {
					x += speed;
					y += JUMP_STRENGHT;
				}
				else {
					randomizeDirection();
				} 
			}
			
			case LEFT -> {
				if (x > 2*GameConstants.TILE_SIZE + speed) {
	                x -= speed;
	                y += JUMP_STRENGHT;
	            }
				else {
					randomizeDirection();
				}
			}
			
			default ->{}
			}
			y-=JUMP_STRENGHT;
		}
		
		updateHitbox();
		setChanged();
        notifyObservers();
	}
	
	
	private void randomizeDirection() {
        setDirection((Math.random()<=0.5) ? Direction.LEFT : Direction.RIGHT);
	}
	
	
	private boolean isNextStepOnTile(boolean right) {
		if (right == true) {
			x += this.speed;
			collisionChecker.checkTileCollision(this);
			x -= this.speed;
			if (!collisionDown) return false;
			else return true;
		} else {
			x -= this.speed;
            collisionChecker.checkTileCollision(this);
            x += this.speed;
            if (!collisionDown) return false;
            else return true;
		}
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
	
}
