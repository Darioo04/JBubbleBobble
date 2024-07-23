package model;


import java.util.Observable;

@SuppressWarnings("deprecation")

public abstract class Entity extends Observable{
	
	protected int x, y;
	protected String path;
	protected String name;
	private boolean isDead;
	
	public Entity(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name=name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
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
        setChanged();
        notifyObservers();
    }
	
	public void setY(int y) {
		this.y = y;
        setChanged();
        notifyObservers();
	}
	
	public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        setChanged();
        notifyObservers();
    }
	
	public boolean isDead() {
        return isDead;
    }
	
	public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

}