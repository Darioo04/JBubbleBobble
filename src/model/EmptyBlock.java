package model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class EmptyBlock extends Tile {
	
	public EmptyBlock() {
		this.collision=false;
		try {
            this.sprite = ImageIO.read(getClass().getResource("/sprites/Tiles/EmptyBlock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	
}
