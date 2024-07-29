package model;


import java.awt.image.BufferedImage;



public abstract class Tile{
	protected BufferedImage sprite;
	protected boolean collision;
//	protected String tilePath;
//	
//	
//	public String getPath() {
//		return tilePath;
//	}
	
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
