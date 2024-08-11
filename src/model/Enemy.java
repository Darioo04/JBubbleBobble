package model;

import java.awt.Rectangle;

public abstract class Enemy extends Entity {
	
	protected Player player;
	protected CollisionChecker collisionChecker;
	private boolean inBubble;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		player = Player.getInstance();
		collisionChecker = CollisionChecker.getInstance();
		hitboxOffsetX = GameConstants.SCALE;
		hitboxOffsetY = GameConstants.SCALE;
		hitboxHeight = GameConstants.TILE_SIZE - 2*GameConstants.SCALE;
		hitboxWidth = GameConstants.TILE_SIZE - 2*GameConstants.SCALE;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));	
	}
	
	public void setInBubble(boolean inBubble) {
		this.inBubble=inBubble;
		update();
	}
	
	public boolean isInBubble() {
		return inBubble;
	}
	
	public void uodate() {
		super.update();
		if (isInBubble()) {
			
		}
		if (isDead()) {
			
		}
	}
	
}
