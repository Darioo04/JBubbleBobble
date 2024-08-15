package model;

import java.awt.Rectangle;

public class PowerUp {
	
	private PowerUpType powerUp;
	private int x;
	private int y;
	private Rectangle hitbox;
	
	public PowerUp(PowerUpType powerUp, int x, int y) {
		this.powerUp = powerUp;
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		Player player = Player.getInstance();
		if (player.getHitbox().intersects(hitbox)) {
			powerUp.applyPowerUp(player);
		}
	}
}
