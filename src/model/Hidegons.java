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
		super.update();
		setEnemyCollision();
		int speed = getSpeed();
		if (!isDead() && !isInBubble() && !isFrozen()) {
			if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
				randomizeDirection();
			}
			else if (!collisionDown && y + Math.abs(speed) <  GameConstants.SCREEN_HEIGHT - 2 * GameConstants.TILE_SIZE) {
	                y += Math.abs(speed);}
			
			else {
				switch (direction) {
					case RIGHT -> {
						if(!collisionRight ) {
							x += speed;
						}else {
							randomizeDirection();
						}
					}
					
					case LEFT -> {
						if(!collisionLeft ) {
		                    x -= speed;
		                }else {
							randomizeDirection();
						}
					 
					}
					default ->{}
				}
			}
		}
		
		shot();
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
		
        if (randomNumber <= 0.5) {
        	setDirection(Direction.LEFT);
        }
        else if (randomNumber>0.5) {
            setDirection(Direction.RIGHT);
        }
        
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
	private void setEnemyCollision() {
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
		collisionChecker.checkTileCollision(this);
	}
}
