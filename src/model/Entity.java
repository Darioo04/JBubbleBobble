package model;


import java.awt.Rectangle;
import java.util.Observable;


@SuppressWarnings("deprecation")

public abstract class Entity extends Observable {
	
	enum Direction {
		UP,DOWN,LEFT,RIGHT
	}
	
	private Direction direction;
	protected CollisionChecker collisionChecker;
	protected int x, y, col, row;
	protected String path;
	private boolean isDead;
	private boolean isMoving;
	private int speed;
	private int fallingSpeed; //velocita di caduta
	public static final int GRAVITY = 2; // Gravità costante
	
	private Rectangle hitbox;
	protected int hitboxWidth;
	protected int hitboxHeight;
	protected int hitboxOffsetX;
    protected int hitboxOffsetY;
	protected boolean collisionDown;
	protected boolean collisionLeft;
	protected boolean collisionRight;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		collisionChecker = CollisionChecker.getInstance();
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction=direction;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path=path;
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
	
	public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        if (x < 0) x = 0;
        if (x > GameConstants.SCREEN_WIDTH) x = GameConstants.SCREEN_WIDTH-1;
        if (y < 0) y = 0;
        if (y > GameConstants.SCREEN_HEIGHT) y = GameConstants.SCREEN_HEIGHT-1;
        update();
    }
	
	public boolean isDead() {
        return isDead;
    }
	
	public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
	
	public void update() {
		setChanged();
        notifyObservers();
	}
	
//	public abstract int getSpeed();
//	public abstract int getFallingSpeed();
	
	public Rectangle getHitbox() {
        return hitbox;
    }
	
	public void setHitboxWidth(int hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
    }
	
	public void setHitboxHeight(int hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
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
    
    public boolean isMoving() {
        return isMoving;
    }
    
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public int getSpeed() {
    	return speed;
    }
    public void setSpeed(int speed) {
    	this.speed=speed;
    }
    
    public int getFallingSpeed() {
    	return fallingSpeed;
    }
    public void setFallingSpeed(int fallingSpeed) {
    	this.fallingSpeed=fallingSpeed;
    }
    

}