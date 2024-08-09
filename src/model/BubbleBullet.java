package model;

import java.awt.Rectangle;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class BubbleBullet extends Observable {
		private int x, y;
		private CollisionChecker collisionChecker;
		
		private Rectangle hitbox;
		private int hitboxWidth;
		private int hitboxHeight;
		private int hitboxOffsetX;
		private int hitboxOffsetY;
		
		public BubbleBullet(int x, int y) {
			this.x = x;
            this.y = y;
            collisionChecker = CollisionChecker.getInstance();
            hitboxWidth = GameConstants.BUBBLE_SHOT_SIZE - 2*GameConstants.SCALE;
            hitboxHeight = GameConstants.BUBBLE_SHOT_SIZE - 2*GameConstants.SCALE;
            hitboxOffsetX = GameConstants.SCALE;
            hitboxOffsetY = GameConstants.SCALE;
            hitbox = new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight);
		}
		
		public void update() {
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
}
