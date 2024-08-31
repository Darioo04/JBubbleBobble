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
	private Player player = Player.getInstance();
	private GameController gameController = GameController.getInstance();
	
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
		player.setIsPowered(true);
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
