package model;


import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Tile  {
	
	public Wall(int level) {
		this.collision=true;
		try {
            this.sprite = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-"+(level-1)+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
}
