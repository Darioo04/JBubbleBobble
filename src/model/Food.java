package model;

import java.awt.Rectangle;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class Food extends Observable{
	private FoodType type;
	private int points;
	private int x;
	private int y;
	private Rectangle hitbox;
	private int hitboxWidth;
    private int hitboxHeight;
    private int hitboxOffsetX;
    private int hitboxOffsetY;
    private boolean collisionDown;
    private boolean isCollected;
	
	public Food(FoodType type, int x, int y) {
		this.x = x;
		this.y = y;
		this.type=type;
		this.points=type.getPoint();
		hitboxWidth = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxHeight = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxOffsetX = GameConstants.SCALE;
        hitboxOffsetY = GameConstants.SCALE;
		setHitbox(new Rectangle(x+hitboxOffsetX, y+hitboxOffsetY, hitboxWidth, hitboxHeight));
	}

	public int getPoints() {
		return points;
	}	
	public FoodType getType() {
		return type;
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
	
	public void setHitbox(Rectangle hitBox) {
        this.hitbox = hitBox;
    }
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void updateHitbox() {
		setHitboxX(x + hitboxOffsetX);
		setHitboxY(y + hitboxOffsetY);
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
	
    public void setCollisionDown(boolean collisionDown) {
        this.collisionDown = collisionDown;
    }
    
    public boolean getCollisionDown() {
        return collisionDown;
    }
    
    public void setCollected(boolean isCollected) {
    	this.isCollected = isCollected;
    }
    
    public boolean isCollected() {
    	return isCollected;
    }
	
	public void update() {
		CollisionChecker.getInstance().checkTileCollision(this);
		CollisionChecker.getInstance().checkFoodPlayerCollision(this, Player.getInstance());
		if (!getCollisionDown() && y<GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE) {
			y+=GameConstants.OBJECT_SPEED;
		}
		else if (y>GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE) {
			y = GameConstants.TILE_SIZE;
		}
		
		updateHitbox();
		setChanged();
		notifyObservers();
	}
	
}
