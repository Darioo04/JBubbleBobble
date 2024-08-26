package model;

import java.awt.Rectangle;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class Food extends Observable {
	private FoodType type;
	private int points;
	private int x;
	private int y;
	private Rectangle hitbox;
	private int hitboxWidth;
    private int hitboxHeight;
    private int hitboxOffsetX;
    private int hitboxOffsetY;
	
	public Food(FoodType type) {
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
	
	public void update() {
		setChanged();
		notifyObservers();
	}
}
