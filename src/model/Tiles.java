package model;


import java.util.Observable;

@SuppressWarnings("deprecation")


public abstract class Tiles extends Observable {
	protected boolean collision;
	protected String tilePath;
	
	
	public String getPath() {
		return tilePath;
	}
	
	public boolean isCollision() {
		return collision;
	}
}
