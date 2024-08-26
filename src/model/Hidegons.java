package model;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Hidegons extends Enemy{
	// spara palle di fuoco, si muove solo a destra e a sinistra
	private FireBall fireBall;
	public Hidegons(int x,int y) {
		super(x,y);
		setDirection(Direction.RIGHT);
		setPath("/sprites/hidegons/");
		setSpeed(3);
		setNumIdleSprites(1);
		setNumRunningSprites(3);
	}
	
	@Override
	public void update() {
		collisionChecker.checkTileCollision(this);
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
		shot();
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
        setDirection((Math.random()<=0.5) ? Direction.LEFT : Direction.RIGHT);
	}
	
	private void shot() {
		if (isPlayerForward()) {
			GameController.getInstance().addObj( new FireBall(getX(),getY(),direction) );
		}
//		new Fireball(getX(),getY(),getDirection());
	}
	
	private boolean isPlayerForward() {
		switch (direction) {
			case RIGHT -> {
				int x = getX();
				while (!getCollisionRight()) {
					x++;
					setHitboxX(x);
					collisionChecker.checkTileCollision(this);
					
				}
				setHitboxX(getX());
				int diff = x - getX();
				for (int i=0; i<diff; i++) {
					if (getX()+i==player.getX()) return true;
				}
			}
			
			case LEFT -> {
				int x = getX();
				while (!getCollisionLeft()) {
					x--;
					setHitboxX(x);
					collisionChecker.checkTileCollision(this);
					
				}
				setHitboxX(getX());
				int diff = x - getX();
				for (int i=0; i<diff; i++) {
					if (getX() - i==player.getX()) return true;
				}
			}
			
			default -> {
				
			}
		
		}
		return false;
	}
}
