package model.bubble;

import java.util.Observable;

@SuppressWarnings("deprecation")

public abstract class Bubble extends Observable  {
	protected final int WIDTH = 600;
	protected final int HEIGHT = 400;
	protected final int RADIUS = 40;
	protected int x;
	protected int y;
	
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
	public abstract void shot();
		
	
}
