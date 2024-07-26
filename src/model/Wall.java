package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Tiles  {
	
	public Wall(int level) {
		this.collision=true;
		this.tilePath = "/sprite/Tiles/LevelTiles-"+level+".png"; 
	}
}
