package model;

import java.awt.Rectangle;
import java.util.Observable;

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
	private Player player;
	
	public PowerUp(PowerUpType type, int x, int y) {
		player = Player.getInstance();
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
		if (player.getHitbox().intersects(hitbox)) {
			this.setHitbox(new Rectangle(0, 0, 1, 1));
			switch (type) {
			case PINK_CANDY -> {
				
			}
			
			
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + type);
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
}
