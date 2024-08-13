package model;

public abstract class Tile {
	private String path;
	private boolean collision;

	public Tile(boolean collision) {
		this.collision=collision;
	}
	
	public boolean hasCollision() {
		return collision;
	}
	
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	} 
}
