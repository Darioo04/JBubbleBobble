package model;

import java.awt.Rectangle;
import java.util.Observable;

import view.ObjView;
import view.WaterView;

@SuppressWarnings("deprecation")
public class Water extends ObjModel {

	private int x, y;
	private Rectangle hitbox;
	private int hitboxWidth;
	private int hitboxHeight;
	private int hitboxOffsetX;
	private int hitboxOffsetY;
	private Direction direction;
	protected boolean collisionDown;
	protected boolean collisionLeft;
	protected boolean collisionRight;
	
	private ObjView waterView;
	
	public Water (int x, int y, Direction direction) {
		super(x, y);
		this.direction = direction;
		hitboxWidth = GameConstants.WATER_SIZE;
		hitboxHeight = GameConstants.WATER_SIZE;
		hitboxOffsetX = 0;
		hitboxOffsetY = 0;
		hitbox = new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight);
		setPath("/sprites/Obj/water-");
		setNumSprites(2);
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	public Rectangle getHitbox() {
		return hitbox;
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
    
    public void setWaterView(ObjView waterView) {
        this.waterView = waterView;
        addObserver(waterView);
    }
    
    public ObjView getWaterView() {
    	return waterView;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
