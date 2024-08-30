package model;

import java.awt.Rectangle;
import java.util.Observable;

import controller.GameController;

@SuppressWarnings("deprecation")

public class PowerUp extends Observable{
	
	private PowerUpType type;
	private int x;
	private int y;
	private Rectangle hitbox;
	private int hitboxWidth;
    private int hitboxHeight;
    private int hitboxOffsetX;
    private int hitboxOffsetY;
	private boolean canBeDeleted;
	
	public PowerUp(PowerUpType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		hitboxWidth = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxHeight = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxOffsetX = GameConstants.SCALE;
        hitboxOffsetY = GameConstants.SCALE;
		setHitbox(new Rectangle(x+hitboxOffsetX, y+hitboxOffsetY, hitboxWidth, hitboxHeight));
	}
	
	public void update() {
		CollisionChecker.getInstance().checkPlayerPowerUpCollision(Player.getInstance(),this);
		
	}
	
	public void applyPowerUp() {
		switch(type) {
			case PINK_CANDY -> {
				BubbleBullet.increaseBulletDistance();
			}
			case BLUE_CANDY -> {
				BubbleBullet.increaseBulletSpeed();
			}
			case YELLOW_CANDY -> {
				
			}
			case SHOES -> {
				//incrementa la velocità del player (penso di x1.5)
			}
			case CLOCK -> {
				GameController.getInstance().freezeEnemies();
			}
			case DYNAMITE -> {
				GameController.getInstance().killAllEnemies();
			}
			case CHACK_HEART -> {
				GameController.getInstance().freezeEnemies();
			}
			case CRYSTAL_RING -> {
				//il player guadagna 10 punti ad ogni passo che fa (quando x viene incrementato di tot punti)
			}
			case AMETHYST_RING -> {
				//il player guadagna 500 punti ogni volta che salta
			}
			case RUBY_RING -> {
				//il player guadagna 100 punti per ogni bolla che spara
			}
			case CROSS_OF_THUNDER -> {
				//tutti i nemici nel livello vengono uccisi
			}
			case BLUE_LAMP -> {
				//da al player l'abilita dei tre anelli (CRYSTAL_RING, AMETHYST_RING E RUBY_RING)
			}
		}
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setHitbox(Rectangle hitBox) {
        this.hitbox = hitBox;
    }
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public PowerUpType getType() {
		return type;
	}
	
	public void setCanBeDeleted(boolean canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	
	public boolean canBeDeleted() {
		return canBeDeleted;
	}
}
