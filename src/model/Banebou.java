package model;

import controller.GameController;

@SuppressWarnings("deprecation")

public class Banebou extends Enemy {
	//si muovono saltando ad arco
	private static final int JUMP_STRENGHT = 4*GameConstants.SCALE;
	private int targetY;
	private boolean isJumping=false;
	private boolean inAir=false;
	private int fallingSpeed = 0;
	
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
		collisionChecker.checkTileCollision(this);
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
						if(x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed && !getCollisionRight()) {
							x += 25;
							y-=70;
						}else {
							randomizeDirection();
						}
					}
					
					case LEFT -> {
						if(x > 2*GameConstants.TILE_SIZE + speed && !getCollisionLeft()) {
		                    x -= 25;
		                    y-=70;
		                }else {
							randomizeDirection();
						}
					 
					}
					
					default ->{}
				}
			}
		}
		updateHitbox();
		//shot();
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
		double randomNumber = Math.random();
		
        if (randomNumber <= 0.50) {
        	setDirection(Direction.LEFT);
        }
        else{
            setDirection(Direction.RIGHT);
        }
        
	}
	
	//private void shot() {
		//if (isPlayerForward()) {
		//	GameController.getInstance().addObj( new FireBall(getX(),getY(),direction) );
		//}
//		new Fireball(getX(),getY(),getDirection());
	//}
	
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
	private boolean hasTilesAbove() {
		int row = y / GameConstants.TILE_SIZE;
		int col = x / GameConstants.TILE_SIZE;
		
        for (int i = row - 1; i >= 2; i--) {
        	if (levelFile[i][col] == '1') {
        		return true;
        	}
        }
        return false;
	}
}
