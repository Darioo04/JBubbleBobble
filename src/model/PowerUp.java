package model;

import java.awt.Rectangle;

public class PowerUp {
	
	private PowerUpType powerUp;
	private int x;
	private int y;
	private Rectangle hitbox;
	private int hitboxWidth;
    private int hitboxHeight;
    private int hitboxOffsetX;
    private int hitboxOffsetY;
	private Player player;
	
	public PowerUp(PowerUpType powerUp, int x, int y) {
		player = Player.getInstance();
		this.powerUp = powerUp;
		this.x = x;
		this.y = y;
		hitboxWidth = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxHeight = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxOffsetX = GameConstants.SCALE;
        hitboxOffsetY = GameConstants.SCALE;
		setHitbox(new Rectangle(x+hitboxOffsetX, y+hitboxOffsetY, hitboxWidth, hitboxHeight));
	}
	
	public void update() {
		if (player.getHitbox().intersects(hitbox)) {
			this.setHitbox(new Rectangle(0, 0, 1, 1));
			switch (powerUp) {
			case PowerUpType.PINK_CANDY -> {
				
			}
			
			
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + powerUp);
			}
		}
	}
	
	public void setHitbox(Rectangle hitBox) {
        this.hitbox = hitBox;
    }
	
	public Rectangle getHitbox() {
		return hitbox;
	}
}
