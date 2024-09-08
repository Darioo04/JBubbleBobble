package model;

import java.awt.Rectangle;
import java.util.Observable;

@SuppressWarnings("deprecation")

public abstract class GameObject extends Observable {
	
	protected int x,y;
	protected Rectangle hitbox;
	protected int hitboxWidth;
	protected int hitboxHeight;
	protected int hitboxOffsetX;
    protected int hitboxOffsetY;
	protected boolean collisionDown;
	protected boolean collisionLeft;
	protected boolean collisionRight;
	protected boolean collisionUp;
	private boolean canBeDeleted;
	
	public GameObject(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.hitboxOffsetX = GameConstants.SCALE * 2;
		this.hitboxOffsetY = GameConstants.SCALE * 2;
		this.hitboxWidth = size - 2*hitboxOffsetX;
		this.hitboxHeight = size - 2*hitboxOffsetY;
		setHitbox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight));
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void update() {
		setChanged();
        notifyObservers();
	}
	
	public void update(Object arg) {
		setChanged();
		notifyObservers(arg);
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
    
    public boolean getCollisionDown() {
    	return collisionDown;
    }
    
    public void setCollisionDown(boolean collisionDown) {
        this.collisionDown = collisionDown;
    }
    
    public boolean getCollisionLeft() {
        return collisionLeft;
    }
    
    public void setCollisionLeft(boolean collisionLeft) {
        this.collisionLeft = collisionLeft;
    }
    
    public boolean getCollisionRight() {
        return collisionRight;
    }
    
    public void setCollisionRight(boolean collisionRight) {
        this.collisionRight = collisionRight;
    }
    
    public boolean getCollisionUp() {
        return collisionUp;
    }
    
    public void setCollisionUp(boolean collisionUp) {
        this.collisionUp = collisionUp;
    }
    
	public void setCanBeDeleted(boolean canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	
	public boolean canBeDeleted() {
		return canBeDeleted;
	}
}
