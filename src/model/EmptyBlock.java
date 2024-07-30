package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EmptyBlock extends Tile {
	
	public EmptyBlock() {
		super(false);
		try {
            BufferedImage sprite = ImageIO.read(getClass().getResource("/sprites/Tiles/EmptyBlock.png"));
            setSprite(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	
}
