package model;


import java.util.Observable;

@SuppressWarnings("deprecation")

public abstract class ObjModel extends Observable {
	
	private int x;
	private int y;
	private String path;
	
	public ObjModel(int x, int y, String path) {
		this.x=x;
		this.y=y;
		this.path=path;
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
	
	public void update() {
		setChanged();
		notifyObservers();
	}
}
