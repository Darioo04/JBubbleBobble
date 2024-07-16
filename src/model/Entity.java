package model;

import java.nio.file.Path;
import java.util.Observable;

@SuppressWarnings("deprecation")

public abstract class Entity extends Observable{
	
	protected int hp;
	protected int x, y;
	
	public Entity(int hp, int x, int y) {
		this.hp = hp;
		this.x = x;
		this.y = y;
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

}