package model.tiles;

import java.awt.image.BufferedImage;

public class Tiles {
	protected boolean collision;
	protected BufferedImage sprite;
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public boolean isCollision() {
		return collision;
	}
}
