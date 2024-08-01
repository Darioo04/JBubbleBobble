package model;

import java.awt.Rectangle;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	
	enum Direction {
		LEFT,RIGHT
	}
	
	private long score;
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;	
	private boolean isJumping;
	private int speed;
	private Direction direction;
	private int lives;
	private int fallingSpeed; //velocita di caduta
	private static final int JUMP_STRENGTH = 10; // Forza del salto
	
	
	private Player() {
		super(200, 150, "Player");
		setDefaultValues();
		this.hitboxWidth = GameConstants.TILE_SIZE;
		this.hitboxHeight = GameConstants.TILE_SIZE;
		setHitbox(new Rectangle(x, y, hitboxWidth, hitboxHeight));
	}
	
	public static Player getInstance() {
		if (instance==null) instance= new Player();
		return instance;
	}
	
	public void setDefaultValues() {
		this.speed = 7;
		this.lives = 3;
		this.score = 0;
		setDead(false);
		this.isJumping = false;
		this.setPath("/sprites/BubAndBob1/");
		this.direction = Direction.RIGHT;
		fallingSpeed = 0;
	}
	
	public void setDirectionAndCollision() {
		if(isLeftPressed) direction = Direction.LEFT;
		else if(isRightPressed) direction = Direction.RIGHT;
		//direction = (isLeftPressed) ? Direction.LEFT : Direction.RIGHT;
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
	}
	
	public void updateHitbox() {
		setHitboxX(x);
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
        } else {
            fallingSpeed = 0;
            isJumping = false;
        }
		
		switch (direction){
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
		
			default -> throw new IllegalArgumentException("Unexpected value: " + direction);
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

	@Override
	public int getSpeed() {
		return speed;
	}
	
	public boolean isFalling() {
		return fallingSpeed > 0;
	}

	@Override
	public int getFallingSpeed() {
		return fallingSpeed;
	}
}
