package model;

import java.awt.Rectangle;

import controller.GameController;
import controller.LevelCreator;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	/**
	 * Questa classe rappresenta un giocatore nel gioco.
	 */
	
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;	
	private boolean isJumping;
	private boolean canShoot=true;
	private int speed;
	private int lives;
	private boolean lostLife = false;
	private final int DEATH_DELAY = 30;
	private int JUMP_STRENGTH = 9 * GameConstants.SCALE; // Forza del salto
	private boolean inAir;
	private boolean isInvincible; //indica che il player è invincibile
	private boolean crystalRingPower; //indica che il crystal ring è attivo
	private boolean amethystRingPower; // indica che l'amethyst ring è attivo
	private boolean rubyRingPower; //indica che il ruby ring è attivo
	private int fireRate = GameConstants.PLAYER_FIRE_RATE; //rateo di fuoco del player
	private int shoesDistance = 15*GameConstants.SCREEN_WIDTH; //distanza da percorrere affinché possa spawnare il power up shoes
	
	/*
	 * condizioni affinché i power up possano spawnare
	 */
	private int bubblesPopped; 
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
	
	/*
	 * metodo per assegnare valori di default al player 
	 */
	
	public void setDefaultValues() {
		this.speed = 7;
		this.lives = 3;
		setDead(false);
		inAir = false;
		this.setPath("/sprites/BubAndBob1/Bub-");
		setDirection(Direction.RIGHT);
		fallingSpeed = 0;
		setRightPressed(false);
		setLeftPressed(false);
		setSpacePressed(false);
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
	/*
	 * metodo che si occupa di generare la BubbleBullet da far sparare al player
	 */
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
		if (rubyRingPower) { //se il power up è attivo il player guadagna punti
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
		if (amethystRingPower) { //se il power up è attivo il player guadagna punti
			GameController.getInstance().addScore(500);
		}
	}
	
	@Override
	public void update() {
		setDirectionAndCollision();
		collisionChecker.checkTileCollision(this);
		
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
			if (crystalRingPower) { //se il power up è attivo il player guadagna punti
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
		
		updateHitbox();
        setChanged();
        notifyObservers();
//        System.out.println("x: " + x + "  y: " + y + "	left: " + collisionLeft + "  right: " + collisionRight + "  down: " + collisionDown + "  up: " + collisionUp + "   fSpeed: " + fallingSpeed + "   inAir: " + inAir + "   isJumping: " + isJumping + "   leftX: " + getHitboxX() + "  rightX: " + (getHitboxX()+hitboxWidth) + "  bottomY: " + (getHitboxY()+getHitboxHeight()));
	}
	/*
	 * metodo che si occupa di generare a schermo il player
	 */
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
	
	public void increaseLives() {
		lives++;
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
	
	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}
	
	public boolean canShoot() {
		return canShoot;
	}
	
	public void increaseBubblesPopped() {
		bubblesPopped++;
	}
	public int getBubblesPopped() {
		return bubblesPopped;
	}
	public void resetBubblesPopped() {
		bubblesPopped = 0;
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
    
    public void increaseThunderBubblesPopped() {
    	lightningBubblesPopped++;
    }
    public int getThunderBubblesPopped() {
    	return lightningBubblesPopped;
    }
    public void resetThunderBubblesPopped() {
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
	
	public void setInvicibility(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}
	
	public boolean isInvincible() {
		return isInvincible;
	}
	
}
