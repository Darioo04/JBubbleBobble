package model;

import controller.GameController;
import view.PowerUpView;

@SuppressWarnings("deprecation")

public class PowerUp extends GameObject{
	
	private PowerUpType type;
	private int poweredCounter;
	private final int POWERED_TIME = 480;	//8 secondi
	private PowerUpView powerUpView;
	private Player player = Player.getInstance();
	private GameController gameController = GameController.getInstance();
	
	public PowerUp(PowerUpType type, int x, int y) {
		super(x,y, GameConstants.ITEM_SIZE);
		this.type = type;
	}
	
	public void update() {
		CollisionChecker.getInstance().checkPlayerPowerUpCollision(Player.getInstance(),this);
		if (poweredCounter > 0) {
			poweredCounter++;
			if (poweredCounter>=POWERED_TIME) {
				removePowerUp();
				setCanBeDeleted(true);
			}
		}
		
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
	
	public PowerUpType getType() {
		return type;
	}
	
	public void setPowerUpView(PowerUpView powerUpView) {
		this.powerUpView = powerUpView;
	}
	
	public PowerUpView getPowerUpView() {
		return powerUpView;
	}
}
