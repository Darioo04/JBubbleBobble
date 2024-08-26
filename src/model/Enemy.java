package model;

import java.awt.Rectangle;

public abstract class Enemy extends Entity {
	
	protected Player player;
	protected CollisionChecker collisionChecker;
	private boolean inBubble;
	private boolean isJumping;
	private boolean isChasingPlayer = false;
	private boolean bubbleExploded;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		setSpawnX(x);
		setSpawnY(y);
		player = Player.getInstance();
		collisionChecker = CollisionChecker.getInstance();
		hitboxOffsetX = GameConstants.SCALE;
		hitboxOffsetY = GameConstants.SCALE;
		hitboxHeight = GameConstants.TILE_SIZE - 2*GameConstants.SCALE;
		hitboxWidth = GameConstants.TILE_SIZE - 2*GameConstants.SCALE;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));	
		
		setMoving(true);
	}
	
	public void setInBubble(boolean inBubble) {
		if (!isDead()) this.inBubble=inBubble;
	}
	
	public boolean isInBubble() {
		return inBubble;
	}
	
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public boolean isJumping() {
		return isJumping;
	}
	
	public void setIsChasingPlayer(boolean isChasingPlayer) {
		this.isChasingPlayer=isChasingPlayer;
	}
	
	public boolean isChasingPlayer() {
		return isChasingPlayer;
	}
	
	public void setBubbleExploded(boolean bubbleExploded) {
		this.bubbleExploded = bubbleExploded;
	}
	
	public boolean getBubbleExploded() {
		return bubbleExploded;
	}
	
	@Override
	public void update() {
		if (inBubble) {
			//implementare il comportamento del nemico quando è dentro la bolla
			setY((int)(getY()-GameConstants.BUBBLE_FLOATING_SPEED));
			if (y < GameConstants.TILE_SIZE) y = GameConstants.TILE_SIZE;
			setIsChasingPlayer(false);
			setMoving(false);
		}
		else if (bubbleExploded) {
			y -= (int)GameConstants.BUBBLE_FLOATING_SPEED;
			if (getCollisionDown()) {
				setDead(true);
			}
		}
	}
	
}
