package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.GameController;
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
	private int JUMP_STRENGTH = 9 * GameConstants.SCALE; // Forza del salto
	private boolean inAir;
	private List<BubbleBullet> bubbles;
	
	private Player() {
		setDefaultValues();
		this.hitboxOffsetX = GameConstants.SCALE * 2;
		this.hitboxOffsetY = GameConstants.SCALE * 2;
		this.hitboxWidth = GameConstants.PLAYER_SIZE - 2*hitboxOffsetX;
		this.hitboxHeight = GameConstants.PLAYER_SIZE - hitboxOffsetY;
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
		bubbles = new ArrayList<BubbleBullet>();
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
	
	public BubbleBullet shot() {
//		Random random = new Random();
//		Bubble bubble = BubbleFactory.getInstance().createBubble(random.nextInt(100));
//		bubble.shot();
//		bubbles.add(bubble);
		int bubbleY = this.getHitboxY() + (GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 4;
		int bubbleX = 0;
		switch (direction){
		case Direction.RIGHT ->{
			bubbleX = this.getHitboxX() + this.hitboxWidth + (GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 4;
		}
		
		case Direction.LEFT ->{
            bubbleX = this.getHitboxX() - GameConstants.BUBBLE_SHOT_SIZE - (GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 4;
        }
		
		default ->{}
		}
		
		return new BubbleBullet(bubbleX, bubbleY, this.direction);
	}
	
	public void jump() {
		if(!inAir) {
			fallingSpeed = -JUMP_STRENGTH;
			inAir = true;
		}
	}
	
	@Override
	public void update() {
		setDirectionAndCollision();
		collisionChecker.checkTileCollision(this);
		
		if(isJumping) jump();
		
		if(!collisionDown) inAir = true;
		
		if (inAir) {
			if (y + fallingSpeed > GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE){
				y = GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE;
			} else if(y + fallingSpeed < GameConstants.TILE_SIZE) {
				y = GameConstants.TILE_SIZE + 1;
                fallingSpeed = 5;
			}else {
				y += fallingSpeed;
				fallingSpeed += GRAVITY;
			}
				
			updateHitbox();
			collisionChecker.checkTileCollision(this);
			if (fallingSpeed > 0 && collisionDown) {
				inAir = false;
				fallingSpeed = 0;
				isJumping = false;
			}
		}

		while (collisionDown && collisionLeft && collisionRight) {
			y -= 1;
			collisionDown = false;
			collisionLeft = false;
			collisionRight = false;
			updateHitbox();
            collisionChecker.checkTileCollision(this);
		}
		
		switch (getDirection()){
			case LEFT -> {
				if (isLeftPressed && !collisionLeft) {
					x -= speed;
				}
			}
		
			case RIGHT -> {
				if (isRightPressed && !collisionRight) {
					x += speed;
				}
			}
		
			default -> throw new IllegalArgumentException("Unexpected value: " + getDirection());
		}
		
		
		updateHitbox();
        setChanged();
        notifyObservers();
//        System.out.println("x: " + x + "  y: " + y + "	left: " + collisionLeft + "  right: " + collisionRight + "  down: " + collisionDown + "   fSpeed: " + fallingSpeed + "   leftX: " + getHitboxX() + "  rightX: " + (getHitboxX()+hitboxWidth) + "  bottomY: " + (getHitboxY()+getHitboxHeight()));
	}
	
	private void correctPosition() {
		Rectangle[][] tilesHitboxes = LevelCreator.getInstance().getTilesHitboxes();
		
		int leftX = getHitboxX();
		int rightX = getHitboxX() + getHitboxWidth();
		int topY = getHitboxY();
		int bottomY = getHitboxY() + getHitboxHeight();
		
		int leftCol = leftX / GameConstants.TILE_SIZE;
		int rightCol = rightX / GameConstants.TILE_SIZE;
		int topRow = topY / GameConstants.TILE_SIZE;
		int bottomRow = bottomY / GameConstants.TILE_SIZE;
		
		while(hitbox.intersects(tilesHitboxes[bottomRow][rightCol]) || hitbox.intersects(tilesHitboxes[topRow][rightCol])) {
			x--;
			updateHitbox();
		}
		while(hitbox.intersects(tilesHitboxes[bottomRow][leftCol]) || hitbox.intersects(tilesHitboxes[topRow][leftCol])) {
			x++;
			updateHitbox();
		}
	}
	
	public void spawnPlayer() {
		char[][] levelFile = LevelCreator.getInstance().getLevel();
		for (int x = 0; x < levelFile[0].length; x++) {
			for (int y = levelFile.length-1; y >= 0; y--) {
                if (levelFile[y][x] == ' ') {
                	this.x = x * GameConstants.TILE_SIZE;
                	this.y = y * GameConstants.TILE_SIZE + GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE;
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
	
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
}
