package model.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

@SuppressWarnings("deprecation")


public class Tiles extends Observable {
	protected boolean collision;
	protected BufferedImage sprite;
	
	public Tiles(int level) {
		this.collision=true;
		try { sprite = ImageIO.read(new File("JBubbleBobble\\res\\sprite\\Tiles\\LevelTiles-"+level+".png")); }
		catch (IOException e) {
			System.out.println("Tile cannot be found");
		}
		
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public boolean isCollision() {
		return collision;
	}
}
