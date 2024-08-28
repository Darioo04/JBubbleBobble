package model;

import java.awt.Rectangle;
import java.util.Observable;

import view.FoodView;

@SuppressWarnings("deprecation")

public class Food extends Observable{
	private FoodType type;
	private FoodView foodView;
	private int points;
	private int x;
	private int y;
	private Rectangle hitbox;
	private int hitboxWidth;
    private int hitboxHeight;
    private int hitboxOffsetX;
    private int hitboxOffsetY;
    private boolean collisionDown;
	
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
	
    public void setCollisionDown(boolean collisionDown) {
        this.collisionDown = collisionDown;
    }
    
    public boolean getCollisionDown() {
        return collisionDown;
    }
    
	
	public void setFoodView(FoodView foodView) {
        this.foodView = foodView;
    }
	
	public FoodView getFoodView() {
        return foodView;
    }
	
	public void update() {
		CollisionChecker.getInstance().checkTileCollision(this);
		if (!getCollisionDown()) {
			y-=30*GameConstants.SCALE;
		}
		setChanged();
		notifyObservers();
	}
	
}
