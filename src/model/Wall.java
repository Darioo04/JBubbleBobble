package model;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Tile  {
	
	public Wall(int level) {
		super(true);
		try {
            BufferedImage sprite = ImageIO.read(getClass().getResource("/sprites/Tiles/LevelTiles-"+(level-1)+".png"));
            setSprite(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
}
