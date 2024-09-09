package model;

import java.awt.Rectangle;
import java.util.Observable;

import controller.GameController;
import view.PowerUpView;

@SuppressWarnings("deprecation")

public class PowerUp extends Observable {
	private int x, y;
	private PowerUpType type;
	private int poweredCounter;
	private Rectangle hitbox;
	private int hitboxWidth;
	private int hitboxHeight;
	private int hitboxOffsetX;
	private int hitboxOffsetY;
    private boolean collisionDown;
	private boolean collisionLeft;
	private boolean collisionRight;
	private boolean collisionUp;
	private boolean canBeDeleted;
	private final int POWERED_TIME = 480;	//8 secondi
	private PowerUpView powerUpView;
	private Player player = Player.getInstance();
	private GameController gameController = GameController.getInstance();
	
	public PowerUp(PowerUpType type, int x, int y) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.hitboxOffsetX = GameConstants.SCALE * 2;
		this.hitboxOffsetY = GameConstants.SCALE * 2;
		this.hitboxWidth = GameConstants.TILE_SIZE - 2*hitboxOffsetX;
		this.hitboxHeight = GameConstants.TILE_SIZE - hitboxOffsetY;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));
	}
	
	public void update() {
		CollisionChecker.getInstance().checkPlayerPowerUpCollision(Player.getInstance(),this);
		if (isInizialized()) {
			poweredCounter++;
			if (poweredCounter>=POWERED_TIME) {
				removePowerUp();
			}
		}
		setChanged();
		notifyObservers();
		
	}
	
	public void applyPowerUp() {
		switch(type) {
			case PINK_CANDY -> {
				gameController.addScore(100);
				BubbleBullet.increaseBulletDistance();
			}
			case BLUE_CANDY -> {
				gameController.addScore(100);
				BubbleBullet.increaseBulletSpeed();
			}
			case YELLOW_CANDY -> {
				gameController.addScore(100);
				player.increaseFireRate();
			}
			case SHOES -> {
				player.increaseSpeed();
				player.resetShoesDistance();
			}
			case CLOCK -> {
				gameController.freezeEnemies();
			}
			case DYNAMITE -> {
				gameController.killAllEnemies();
			}
			case CHACK_HEART -> {
				gameController.freezeEnemies();
			}
			case CRYSTAL_RING -> {
				//il player guadagna 10 punti ad ogni passo che fa (quando x viene incrementato di tot punti)
				gameController.addScore(1000);
				player.activeCrystalRing();
			}
			case AMETHYST_RING -> {
				//il player guadagna 500 punti ogni volta che salta
				gameController.addScore(1000);
				player.activeAmethystRing();
			}
			case RUBY_RING -> {
				//il player guadagna 100 punti per ogni bolla che spara
				gameController.addScore(1000);
				player.activeRubyRing();
			}
			case CROSS_OF_THUNDER -> {
				gameController.addScore(3000);
				gameController.killAllEnemies();
			}
			case BLUE_LAMP -> {
				//da al player l'abilita dei tre anelli (CRYSTAL_RING, AMETHYST_RING E RUBY_RING)
				gameController.addScore(1000);
				player.activeCrystalRing();
				player.activeAmethystRing();
				player.activeRubyRing();
			}
		}
	}
	
	public void removePowerUp() {
		switch(type) {
			case PINK_CANDY -> {
				BubbleBullet.resetBulletDistance();
			}
			case BLUE_CANDY -> {
				BubbleBullet.resetBulletSpeed();
			}
			case YELLOW_CANDY -> {
				player.resetFireRate();
			}
			case SHOES -> {
				player.resetSpeed();
			}
			case CLOCK -> {
				gameController.unfreezeEnemies();
			}
			case CHACK_HEART -> {
				gameController.unfreezeEnemies();
			}
			case CRYSTAL_RING -> {
				//il player guadagna 10 punti ad ogni passo che fa (quando x viene incrementato di tot punti)
				player.removeCrystalRing();
			}
			case AMETHYST_RING -> {
				//il player guadagna 500 punti ogni volta che salta
				player.removeAmethystRing();
			}
			case RUBY_RING -> {
				//il player guadagna 100 punti per ogni bolla che spara
				player.removeRubyRing();
			}
			case BLUE_LAMP -> {
				//da al player l'abilita dei tre anelli (CRYSTAL_RING, AMETHYST_RING E RUBY_RING)
				player.removeCrystalRing();
				player.removeAmethystRing();
				player.removeRubyRing();
			}
			default -> {
				
			}
		}
//		canBeDeleted = true;
	}
	
	public void inizializePoweredTime() {
		poweredCounter= 1;
		applyPowerUp();
	}
	
	public boolean isInizialized() {
		return poweredCounter > 0;
	}
	
	public PowerUpType getType() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Rectangle getHitbox() {
        return hitbox;
    }
	
	public void setHitboxWidth(int hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
    }
	
	public void updateHitbox() {
		setHitboxX(x + hitboxOffsetX);
		setHitboxY(y + hitboxOffsetY);
	}
	
	public int getHitboxWidth() {
		return hitboxWidth;
    }
	
	public void setHitboxHeight(int hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
    }
	
	public int getHitboxHeight() {
        return hitboxHeight;
    }
	
	public void setHitboxOffsetX(int hitboxOffsetX) {
        this.hitboxOffsetX = hitboxOffsetX;
    }
	
	public void setHitboxOffsetY(int hitboxOffsetY) {
        this.hitboxOffsetY = hitboxOffsetY;
    }
	
	public void setHitbox(Rectangle hitBox) {
        this.hitbox = hitBox;
    }
	
	public void setHitboxX(int x){
        hitbox.x = x;
    }
	
    public void setHitboxY(int y){
        hitbox.y = y;
    }
    
    public int getHitboxX() {
    	return hitbox.x;
    }
    
    public int getHitboxY() {
        return hitbox.y;
    }
    
    public boolean getCollisionDown() {
    	return collisionDown;
    }
    
    public void setCollisionDown(boolean collisionDown) {
        this.collisionDown = collisionDown;
    }
    
    public boolean getCollisionLeft() {
        return collisionLeft;
    }
    
    public void setCollisionLeft(boolean collisionLeft) {
        this.collisionLeft = collisionLeft;
    }
    
    public boolean getCollisionRight() {
        return collisionRight;
    }
    
    public void setCollisionRight(boolean collisionRight) {
        this.collisionRight = collisionRight;
    }
    
    public boolean getCollisionUp() {
        return collisionUp;
    }
    
    public void setCollisionUp(boolean collisionUp) {
        this.collisionUp = collisionUp;
    }
    
	public void setCanBeDeleted(boolean canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	
	public boolean canBeDeleted() {
		return canBeDeleted;
	}
}
