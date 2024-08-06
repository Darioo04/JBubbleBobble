package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.LevelCreator;

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
	private boolean lostLife = false;
	private int fallingSpeed; //velocita di caduta
	private int JUMP_STRENGTH = 11 * GameConstants.SCALE; // Forza del salto
	private boolean inAir;
	private List<Bubble> bubbles;
	
	private Player() {
		setDefaultValues();
		this.hitboxOffsetX = GameConstants.SCALE * 2;
		this.hitboxOffsetY = GameConstants.SCALE * 2;
		this.hitboxWidth = GameConstants.TILE_SIZE - 2*hitboxOffsetX;
		this.hitboxHeight = GameConstants.TILE_SIZE - hitboxOffsetY;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));
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
		inAir = false;
		this.setPath("/sprites/BubAndBob1/");
		setDirection(Direction.RIGHT);
		fallingSpeed = 0;
		bubbles = new ArrayList<>();
	}
	
	public void setDirectionAndCollision() {
		if(isLeftPressed) setDirection(Direction.LEFT);
		else if(isRightPressed) setDirection(Direction.RIGHT);
		//direction = (isLeftPressed) ? Direction.LEFT : Direction.RIGHT;
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
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
		Random random = new Random();
		Bubble bubble = BubbleFactory.getInstance().createBubble(random.nextInt(100));
		bubble.shot();
		bubbles.add(bubble);
		setChanged();
		notifyObservers();
	}
	
	public void jump() {
		if(!inAir) {
			fallingSpeed = -JUMP_STRENGTH;
			inAir = true;
		}
	}
	
	@Override
	public void update() {
		super.update();
		setDirectionAndCollision();
		collisionChecker.checkTileCollision(this);
//		while (collisionDown && collisionLeft && collisionRight) {
//			y -= 1;		//se si bugga nei tiles sposto il player di un pixel piu sopra
//			collisionChecker.checkTileCollision(this);
//		}

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
		
//		if(!collisionDown) {
//			inAir = true;
//		}
		
		if (inAir) {
			y += fallingSpeed;
			fallingSpeed += GRAVITY;
			collisionChecker.checkTileCollision(this);
			if (fallingSpeed > 0 && collisionDown) {
				inAir = false;
				fallingSpeed = 0;
//				while (collisionDown && collisionLeft && collisionRight) {
//					y -= 1;
//					collisionChecker.checkTileCollision(this);
//				}
			}
		}
		
		
		updateHitbox();
        setChanged();
        notifyObservers();
	}
	
	public void spawnPlayer() {
		char[][] levelFile = LevelCreator.getInstance().getLevel();
		for (int x = 0; x < levelFile[0].length; x++) {
			for (int y = levelFile.length-1; y >= 0; y--) {
                if (levelFile[y][x] == ' ') {
                	this.x = x * GameConstants.TILE_SIZE;
                	this.y = y * GameConstants.TILE_SIZE;
                	return;
                }
            }
		}
	}
	
	public void drawHitbox(Graphics2D g) {		//per debug, viene chiamata nel gamePanel
		g.setColor(Color.BLUE);
		g.drawRect(getHitboxX(), getHitboxY(), getHitboxWidth(), getHitboxHeight());
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
	
	public boolean isFalling() {
		return fallingSpeed > 0;
	}
	
	public void decreaseLives() {
        if (lives-- == 0) {
            setDead(true);
        }
        lostLife = true;
//      System.out.println(lives);
	}
	
	public boolean getLostLife() {
		return lostLife;
	}
	
	public void setLostLife(boolean lostLife) {
        this.lostLife = lostLife;
    }
}
