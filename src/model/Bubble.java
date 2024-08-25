package model;

import java.awt.Rectangle;
import java.util.Observable;

import view.BubbleView;

@SuppressWarnings("deprecation")

public abstract class Bubble extends Observable  {
	private int x, y;
	private Direction direction;
	private static CollisionChecker collisionChecker = CollisionChecker.getInstance();
	private BubbleView bubbleBulletView;
		
	private int spawnX;
	private int targetX;
	private boolean isExpanded;
		
	private Rectangle hitbox;
	private int hitboxWidth;
	private int hitboxHeight;
	private int hitboxOffsetX;
	private int hitboxOffsetY;
	
	public Bubble(int x,int y) {
		this.x=x;
		this.y=y;
		setExpanded(false);
        setSpawnX(x);
        setHitboxWidth(GameConstants.BUBBLE_SHOT_SIZE - 2*GameConstants.SCALE);
        setHitboxHeight(GameConstants.BUBBLE_SHOT_SIZE - 2*GameConstants.SCALE);
        setHitboxOffsetX(GameConstants.SCALE);
        setHitboxOffsetY(GameConstants.SCALE);
        setHitbox(new Rectangle(x + getHitboxOffsetX(), y + getHitboxOffsetY(), getHitboxWidth(), getHitboxHeight()));
	}
	
	public int getX() {
		return x;
    }
		
	public int getY() {
        return y;
    }
		
	public void setX(int x) {
        this.x = x;
        setChanged();
        notifyObservers();
    }
		
	public void setY(int y) {
        this.y = y;
        setChanged();
        notifyObservers();
    }
	
	public void setSpawnX(int spawnX) {
		this.spawnX=spawnX;
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public void setTargetX(int targetX) {
		this.targetX=targetX;
	}
	
	public int getTargetX() {
		return targetX;
	}
		
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
		
	public void updateHitbox() {
		setHitboxX(x + hitboxOffsetX);
		setHitboxY(y + hitboxOffsetY);
	}
	
	public void setHitboxWidth(int hitboxWidth) {
	    this.hitboxWidth = hitboxWidth;
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
	
	public int getHitboxOffsetX() {
		return hitboxOffsetX;
	}
		
	public void setHitboxOffsetY(int hitboxOffsetY) {
	    this.hitboxOffsetY = hitboxOffsetY;
	}
	
	public int getHitboxOffsetY() {
	    return hitboxOffsetY;
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
	
	public CollisionChecker getCollisionChecker() {
		return collisionChecker;
	}
	
	public boolean isExpanded() {
        return isExpanded;
    }
	    
	public void setExpanded(boolean isExpanded) {
		this.isExpanded=isExpanded;
	}
	    
	public BubbleView getBubbleBulletView() {
		return bubbleBulletView;
	}
	    
	public void setBubbleBulletView(BubbleView bubbleBulletView) {
        this.bubbleBulletView = bubbleBulletView;
        this.addObserver(bubbleBulletView);
    }  
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	public void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}
}
