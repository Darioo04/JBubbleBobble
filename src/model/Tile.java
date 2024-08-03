package model;


import java.awt.image.BufferedImage;



public abstract class Tile {
	private BufferedImage sprite;
	private boolean collision;
//	protected String tilePath;
//	
//	
//	public String getPath() {
//		return tilePath;
//	}
	public Tile(boolean collision) {
		this.collision=collision;
	}
	
	public boolean hasCollision() {
		return collision;
	}
	
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	} 
}
