package model;


import java.awt.Rectangle;
import java.util.Observable;

import view.ObjView;

@SuppressWarnings("deprecation")

public abstract class ObjModel extends Observable {
	
	private int x;
	private int y;
	private String path;
	private int numSprites;
	private int hitboxWidth;
	private int hitboxHeight;
	private int hitboxOffsetX;
	private int hitboxOffsetY;
	private Rectangle hitbox;
	private ObjView objView;
    
	public ObjModel(int x, int y) {
		this.x=x;
		this.y=y;
		this.hitboxOffsetX = GameConstants.SCALE * 2;
		this.hitboxOffsetY = GameConstants.SCALE * 2;
		this.hitboxWidth = GameConstants.PLAYER_SIZE - 2*hitboxOffsetX;
		this.hitboxHeight = GameConstants.PLAYER_SIZE - hitboxOffsetY;
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
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void setNumSprites(int numSprites) {
		this.numSprites = numSprites;
	}
	
	public int getNumSprites() {
		return numSprites;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	public void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}
	
	public void setObjView(ObjView objView) {
		this.objView = objView;
	}
	
	public ObjView getObjView() {
		return objView;
	}
}
