package model.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

@SuppressWarnings("deprecation")


public abstract class Tiles extends Observable {
	protected boolean collision;
	protected BufferedImage sprite;
	
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public boolean isCollision() {
		return collision;
	}
}
