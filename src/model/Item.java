package model;

import java.awt.Rectangle;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class Item extends Observable {
	private ItemType type;
	private int points;
	private String name;
	private int x;
	private int y;
	private Rectangle hitbox;
	private int hitboxWidth;
    private int hitboxHeight;
    private int hitboxOffsetX;
    private int hitboxOffsetY;
	
	public Item(ItemType type) {
		this.type=type;
		this.points=type.getPoint();
		this.name=type.getName();
		hitboxWidth = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxHeight = GameConstants.ITEM_SIZE - 2*GameConstants.SCALE;
        hitboxOffsetX = GameConstants.SCALE;
        hitboxOffsetY = GameConstants.SCALE;
		setHitbox(new Rectangle(x+hitboxOffsetX, y+hitboxOffsetY, hitboxWidth, hitboxHeight));
	}

	public int getPoints() {
		return points;
	}

	public String getName() {
		return name;
	}
	
	public ItemType getType() {
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
}
