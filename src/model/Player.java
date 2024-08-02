package model;

import java.awt.Rectangle;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	
	private long score;
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;	
	private boolean isJumping;
	private int speed;

	private int lives;
	private int fallingSpeed; //velocita di caduta
	private static final int JUMP_STRENGTH = 40; // Forza del salto
	
	
	private Player() {
		super(200, 150);
		setDefaultValues();
		this.hitboxOffsetX = GameConstants.SCALE * 2;
		this.hitboxOffsetY = GameConstants.SCALE;
		this.hitboxWidth = GameConstants.TILE_SIZE - 2*hitboxOffsetX;
		this.hitboxHeight = GameConstants.TILE_SIZE;
		setHitbox(new Rectangle(x + hitboxOffsetX, y, hitboxWidth, hitboxHeight));
	}
	
	public static Player getInstance() {
		if (instance==null) instance= new Player();
		return instance;
	}
	
	public void setDefaultValues() {
		this.x = 200;
		this.y = 150;
		this.speed = 7;
		this.lives = 3;
		this.score = 0;
		setDead(false);
		this.isJumping = false;
		this.setPath("/sprites/BubAndBob1/");
		setDirection(Direction.RIGHT);
		fallingSpeed = 0;
	}
	
	public void setDirectionAndCollision() {
		if(isLeftPressed) setDirection(Direction.LEFT);
		else if(isRightPressed) setDirection(Direction.RIGHT);
		//direction = (isLeftPressed) ? Direction.LEFT : Direction.RIGHT;
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
	}
	
	public void updateHitbox() {
		setHitboxX(x + hitboxOffsetX);
		setHitboxY(y);
	}
	
	public long getScore() {
		return score;
	}
	
	public void addScore(long score) {
		this.score+=score;
		update();
	}
	
	public void resetScore() {
		this.score=0;
	}
	
	public void shot() {
		
	}
	
	public void jump() {
		if(!isJumping) {
			this.fallingSpeed = -JUMP_STRENGTH;
            this.isJumping = true;
            if (x < 0) x = 0;
            if (x > GameConstants.SCREEN_WIDTH) x = GameConstants.SCREEN_WIDTH-1;
            if (y < 0) y = 0;
            if (y > GameConstants.SCREEN_HEIGHT) y = GameConstants.SCREEN_HEIGHT-1;
		}
	}
	
	@Override
	public void update() {
		super.update();
		setDirectionAndCollision();
		collisionChecker.checkTileCollision(this);
//		if (isJumping) {
//			fallingSpeed += GRAVITY; // Aumenta la velocità verso il basso a causa della gravità
//            y += fallingSpeed; // Aggiorna la posizione verticale
//        }
//		
		if(!collisionDown || isJumping) {
			fallingSpeed += GRAVITY; // Aumenta la velocità verso il basso a causa della gravità
            y += fallingSpeed; // Aggiorna la posizione verticale
        } 
		if(collisionDown) {
            fallingSpeed = 0;
            isJumping = false;
        }
		
		switch (getDirection()){
			case LEFT -> {
				if (isLeftPressed && !collisionLeft) {
					x -= speed;
					if (x < 0) x = 0; // Non può essere negativo
				}
			}
		
			case RIGHT -> {
				if (isRightPressed && !collisionRight) {
					x += speed;
					if (x + hitboxWidth > GameConstants.SCREEN_WIDTH) x = GameConstants.SCREEN_WIDTH - hitboxWidth; // Non può essere superiore alla larghezza dello schermo
				}
			}
		
			default -> throw new IllegalArgumentException("Unexpected value: " + getDirection());
		}
		updateHitbox();
        setChanged();
        notifyObservers();
	}
	
	public void setLeftPressed(boolean isLeftPressed) {
		this.isLeftPressed = isLeftPressed;
	}
	
	public boolean isLeftPressed() {
		return isLeftPressed;
	}
	
	public void setRightPressed(boolean isRightPressed) {
        this.isRightPressed = isRightPressed;
    }
	
	public boolean isRightPressed() {
        return isRightPressed;
    }
	
	@Override
	public boolean isMoving() {
		return isLeftPressed || isRightPressed;
	}

//	@Override
//	public int getSpeed() {
//		return speed;
//	}
	
	public boolean isFalling() {
		return fallingSpeed > 0;
	}

//	@Override
//	public int getFallingSpeed() {
//		return fallingSpeed;
//	}
}
