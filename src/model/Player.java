package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import controller.GameController;
import controller.LevelCreator;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;	
	private boolean isJumping;
	private int speed;
	private int lives;
	private boolean lostLife = false;
	private int JUMP_STRENGTH = 9 * GameConstants.SCALE; // Forza del salto
	private boolean inAir;
	private boolean isPowered;
	private boolean crystalRingPower;
	private boolean amethystRingPower;
	private boolean rubyRingPower;
	private int fireRate = GameConstants.PLAYER_FIRE_RATE;
	private int shoesDistance = 15*GameConstants.SCREEN_WIDTH;
	
	private int poweredCounter;
	private final int POWERED_TIME = 480;	//8 secondi
	
    private int bubbleBulletsPopped;
    private int fireBubblesPopped;
    private int lightningBubblesPopped;
    private int foodCollected;
    private int blueCandiesCollected;
    private int yellowCandiesCollected;
    private int pinkCandiesCollected;

    private int numJumps;
	
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
		setDead(false);
		inAir = false;
		this.setPath("/sprites/BubAndBob1/Bub-");
		setDirection(Direction.RIGHT);
		fallingSpeed = 0;
		setNumIdleSprites(2);
		setNumRunningSprites(2);
		setNumJumpingSprites(2);
		setNumFallingSprites(2);
	}
	
	public void setDirectionAndCollision() {
		if(isLeftPressed) setDirection(Direction.LEFT);
		else if(isRightPressed) setDirection(Direction.RIGHT);
		collisionLeft = false;
		collisionRight = false;
		collisionDown = false;
		collisionUp = false;
	}
	
	public int getHP() {
		return lives;
	}
	
	public BubbleBullet shot() {
		int bubbleY = this.getHitboxY() + (GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 4;
		int bubbleX = 0;
		switch (direction){
			case RIGHT ->{
				bubbleX = this.getHitboxX() + this.hitboxWidth + (GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 4;
			}
		
			case LEFT ->{
				bubbleX = this.getHitboxX() - GameConstants.BUBBLE_SHOT_SIZE - (GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 4;
			}
		
			default ->{}
		}
		if (rubyRingPower) {
			GameController.getInstance().addScore(100);
		}
		return new BubbleBullet(bubbleX, bubbleY, this.direction);
	}
	
	public void jump() {
		if(!inAir) {
			fallingSpeed = -JUMP_STRENGTH;
			inAir = true;
		}
		numJumps++;
		if (amethystRingPower) {
			GameController.getInstance().addScore(500);
		}
	}
	
	@Override
	public void update() {
		setDirectionAndCollision();
		collisionChecker.checkTileCollision(this);
		
		if(isPowered && poweredCounter == 0) {
			poweredCounter = 1;
		}
		
		if(isJumping) jump();
		
		if(!collisionDown) inAir = true;
		
		if (inAir) {
			if (y + fallingSpeed > GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE) {
				y = GameConstants.TILE_SIZE/2;
				fallingSpeed = 0;
				inAir = true;
			}
			else if(y + fallingSpeed < GameConstants.TILE_SIZE) {
				y = GameConstants.TILE_SIZE + 1;
                fallingSpeed = 5;
			} else {
				y += fallingSpeed;
				fallingSpeed += GRAVITY;
			}
				
			updateHitbox();
			collisionChecker.checkTileCollision(this);
			if (fallingSpeed > 0 && collisionDown && !collisionUp) {
				inAir = false;
				fallingSpeed = 0;
				isJumping = false;
				y -= GameConstants.TILE_SIZE;
				updateHitbox();
				collisionDown = false;						//il player resta incastrato nel tile quando atterra, quindi porto il player sopra di un tile e lo abbasso un pixel alla volta finche non trova la collisione sotto
				collisionChecker.checkTileCollision(this);
				while(!collisionDown || collisionUp) {
					y += 1;
					updateHitbox();
					collisionDown = false;
					collisionChecker.checkTileCollision(this);
				}
				
			}
		}
		if (!isDead()) {
			switch (getDirection()){
				case LEFT -> {
					if (isLeftPressed && !collisionLeft) {
						x -= speed;
						shoesDistance-=speed;
					}
				}
			
				case RIGHT -> {
					if (isRightPressed && !collisionRight) {
						x += speed;
						shoesDistance-=speed;
					}
				}
			
				default -> throw new IllegalArgumentException("Unexpected value: " + getDirection());
			}
			if (crystalRingPower) {
				GameController.getInstance().addScore(10);
			}
		}
		
		
		if (isDead()) {
			if (deathCounter == 0) {
				deathCounter = 1;
				GameController.getInstance().freezeEnemies();
			}
			
			if (deathCounter > 0) {
			    deathCounter++;
			    if (deathCounter >= DEATH_DELAY) {
			    	GameController.getInstance().setGameOver();
			    	deathCounter = 0;
			    	lives = 3;
			    }
			}
		}
		
		if (poweredCounter > 0) {
			poweredCounter++;
            if (poweredCounter >= POWERED_TIME) {
                poweredCounter = 0;
                isPowered = false;
                
            }
		}
		
		updateHitbox();
        setChanged();
        notifyObservers();
//        System.out.println("x: " + x + "  y: " + y + "	left: " + collisionLeft + "  right: " + collisionRight + "  down: " + collisionDown + "  up: " + collisionUp + "   fSpeed: " + fallingSpeed + "   inAir: " + inAir + "   isJumping: " + isJumping + "   leftX: " + getHitboxX() + "  rightX: " + (getHitboxX()+hitboxWidth) + "  bottomY: " + (getHitboxY()+getHitboxHeight()));
	}
	
	public void spawnPlayer() {
		char[][] levelFile = LevelCreator.getInstance().getLevel();
		for (int x = 0; x < levelFile[0].length; x++) {
			for (int y = levelFile.length-1; y >= 0; y--) {
                if (levelFile[y][x] == ' ') {
                	this.x = x * GameConstants.TILE_SIZE + GameConstants.SCALE;
                	this.y = y * GameConstants.TILE_SIZE + GameConstants.TILE_SIZE - GameConstants.PLAYER_SIZE - 1;
                	setSpawnX(x);
                	setSpawnY(y);
                	return;
                }
            }
		}
	}
	
	public void setIsPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}
	
	public boolean isPowered() {
        return isPowered;
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
	
	public void setSpacePressed(boolean isSpacePressed) {
		this.isSpacePressed = isSpacePressed;
	}
	
	public boolean isSpacePressed() {
		return isSpacePressed;
	}
	
	@Override
	public boolean isMoving() {
		return isLeftPressed || isRightPressed || isSpacePressed;
	}
	
	public boolean isFalling() {
		return fallingSpeed > 0;
	}
	
	public boolean isInAir() {
		return inAir;
	}
	
	public void decreaseLives() {
		lives--;
        if (lives == 0) {
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
	
	public boolean isJumping() {
		return isJumping;
	}
	
	
    public void increaseBubbleBulletsPopped() {
    	bubbleBulletsPopped++;
    }
    public int getBubbleBulletsPopped() {
    	return bubbleBulletsPopped;
    }
    public void resetNumBubbleBulletsPopped() {
    	bubbleBulletsPopped=0;
    }
    
    public void increaseFireBubblesPopped() {
    	fireBubblesPopped++;
    }
    public int getFireBubblesPopped() {
    	return fireBubblesPopped;
    }
    public void resetFireBubblesPopped() {
    	fireBubblesPopped=0;
    }
    
    public void increaseLightningBubblesPopped() {
    	lightningBubblesPopped++;
    }
    public int getLightningBubblesPopped() {
    	return lightningBubblesPopped;
    }
    public void resetLightningBubblesPopped() {
    	lightningBubblesPopped=0;
    }
    
    public void increaseBlueCandiesCollected() {
    	blueCandiesCollected++;
    }
    public int getBlueCandiesCollected() {
    	return blueCandiesCollected;
    }
    public void resetBlueCandiesCollected() {
    	blueCandiesCollected=0;
    }
    
    public void increaseYellowCandiesCollected() {
    	yellowCandiesCollected++;
    }
    public int getYellowCandiesCollected() {
    	return yellowCandiesCollected;
    }
    public void resetYellowCandiesCollected() {
    	yellowCandiesCollected=0;
    }
    
    public void increasePinkCandiesCollected() {
    	pinkCandiesCollected++;
    }
    public int getPinkCandiesCollected() {
    	return pinkCandiesCollected;
    }
    public void resetPinkCandiesCollected() {
    	pinkCandiesCollected=0;
    }
    
    public void increaseFoodCollected() {
    	foodCollected++;
    }
    public int getFoodCollected() {
    	return foodCollected;
    }
    public void resetFoodCollected() {
    	foodCollected=0;
    }
    
    public void increaseNumJumps() {
    	numJumps++;
    }
    public int getNumJumps() {
    	return numJumps;
    }
    public void resetNumJumps() {
    	numJumps=0;
    }
    
    public void increaseSpeed() {
    	speed*=GameConstants.SPEED_MULTIPLIER;
    }
    
    public void resetSpeed() {
    	speed/=GameConstants.SPEED_MULTIPLIER;
    }
    
	public void increaseFireRate() {
		fireRate = GameConstants.POWERED_PLAYER_FIRE_RATE;
	}
	
	public void resetFireRate() {
		fireRate = GameConstants.PLAYER_FIRE_RATE;
	}
	
	public int getFireRate() {
		return fireRate;
	}
	
	public void activeCrystalRing() {
		crystalRingPower = true;
	}
	
	public void removeCrystalRing() {
		crystalRingPower = false;
	}
	
	public void activeAmethystRing() {
		amethystRingPower = true;
	}
	
	public void removeAmethystRing() {
		amethystRingPower = false;
	}
	
	public void activeRubyRing() {
		rubyRingPower = true;
	}
	
	public void removeRubyRing() {
		rubyRingPower = false;
	}
	
	public void resetShoesDistance() {
		shoesDistance = 15 *GameConstants.SCREEN_WIDTH;
	}
	
	public boolean shoesCanSpawn() {
		return shoesDistance<=0;
	}
}
