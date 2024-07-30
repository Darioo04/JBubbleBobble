package model;


import java.awt.Rectangle;
import java.util.List;
import java.util.Observable;


@SuppressWarnings("deprecation")

public abstract class Entity extends Observable {
	
	
	protected int x, y;
	protected List<String> paths;
	protected String path;
	protected String name;
	private boolean isDead;
	private boolean isMoving;
	public static final int GRAVITY = 1; // Gravità costante
	
	private Rectangle hitbox;
	protected int hitboxWidth;
	protected int hitboxHeight;
	
	public Entity(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name=name;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path=path;
	}
	
	public void addPath(String path) {
		paths.add(path);
	}
	
	public String getName() {
		return name;
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
        if (x > GameConstants.SCREEN_WIDTH) x = GameConstants.SCREEN_WIDTH;
        if (y < 0) y = 0;
        if (y > GameConstants.SCREEN_HEIGHT) y = GameConstants.SCREEN_HEIGHT;
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
	
	public Rectangle getHitbox() {
        return hitbox;
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

}