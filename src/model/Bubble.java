package model;

import java.util.Observable;

@SuppressWarnings("deprecation")

public abstract class Bubble extends Observable  {
	private int x;
	private int y;
	
	public Bubble(int x,int y) {
		this.x=x;
		this.y=y;
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
        update();
    }
	
	public abstract void shot(); //metodo astratto implementato da tutte le bolle
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	
}
