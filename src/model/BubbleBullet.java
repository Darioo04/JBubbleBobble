package model;

import java.awt.Rectangle;
import java.util.Observable;

import view.BubbleBulletView;

@SuppressWarnings("deprecation")

public class BubbleBullet extends Observable {
		private int x, y;
		private Direction direction;
		private CollisionChecker collisionChecker;
		private BubbleBulletView bubbleBulletView;
		
		private int spawnX;
		private int targetX;
		private boolean isExpanded;
		
		private Rectangle hitbox;
		private int hitboxWidth;
		private int hitboxHeight;
		private int hitboxOffsetX;
		private int hitboxOffsetY;
		
		
		public BubbleBullet(int x, int y, Direction direction) {
			this.direction = direction;
			isExpanded = false;
			this.x = x;
            this.y = y;
            spawnX = x;
            targetX = (direction == Direction.RIGHT) ? spawnX + GameConstants.BUBBLE_X_DISTANCE : spawnX - GameConstants.BUBBLE_X_DISTANCE;
            collisionChecker = CollisionChecker.getInstance();
            hitboxWidth = GameConstants.BUBBLE_SHOT_SIZE - 2*GameConstants.SCALE;
            hitboxHeight = GameConstants.BUBBLE_SHOT_SIZE - 2*GameConstants.SCALE;
            hitboxOffsetX = GameConstants.SCALE;
            hitboxOffsetY = GameConstants.SCALE;
            hitbox = new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight);
		}
		
		public void update() {
			if(!isExpanded) {
				switch (direction){
					case RIGHT -> {
						if(x != targetX)
							x += GameConstants.BUBBLE_X_SPEED;
						if(x > GameConstants.SCREEN_WIDTH - 2*GameConstants.TILE_SIZE) {
							x = GameConstants.SCREEN_WIDTH - 2*GameConstants.TILE_SIZE - GameConstants.BUBBLE_SHOT_SIZE;
							targetX = x;
						}
					}
				
					case LEFT -> {
						if(x!= targetX)
							x -= GameConstants.BUBBLE_X_SPEED;
						if(x < 2*GameConstants.TILE_SIZE) {
							x = GameConstants.TILE_SIZE / 2 * 3;
							targetX = x;
						}
					}
				
					default -> throw new IllegalArgumentException("Unexpected value: " + direction);
				}
				
				if (targetX == x) {
					isExpanded = true;
					if (direction == Direction.RIGHT) {
						x = x - (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2;
					}
					else {
						x = x + (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2;
					}
					y = y - (GameConstants.BUBBLE_EXPANDED_SIZE - GameConstants.BUBBLE_SHOT_SIZE) / 2;
					hitboxWidth = GameConstants.BUBBLE_EXPANDED_SIZE - 2*GameConstants.SCALE;
		            hitboxHeight = GameConstants.BUBBLE_EXPANDED_SIZE - 2*GameConstants.SCALE;
		            hitbox = new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight);
				}
				
			}else {
				
			}
			
			updateHitbox();
            setChanged();
            notifyObservers();
		}
		
		public int getX() {
            return x;
        }
		
		public int getY() {
            return y;
        }
		
		public void setX(int x) {
            this.x = x;
            update();
        }
		
		public void setY(int y) {
            this.y = y;
            update();
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
	    
	    public boolean isExpanded() {
            return isExpanded;
        }
	    
	    public BubbleBulletView getBubbleBulletView() {
	    	return bubbleBulletView;
	    }
	    
	    public void setBubbleBulletView(BubbleBulletView bubbleBulletView) {
            this.bubbleBulletView = bubbleBulletView;
            this.addObserver(bubbleBulletView);
        }
	    
	    
}
