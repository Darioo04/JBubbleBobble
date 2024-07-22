package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Tiles  {
	
	public Wall(int level) {
		this.collision=true;
		try { this.sprite = ImageIO.read(new File("JBubbleBobble\\res\\sprite\\Tiles\\LevelTiles-"+level+".png")); }
		catch (IOException e) {
			System.out.println("Tile cannot be found");
		}
	}
}
