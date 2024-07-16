package model.tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wall extends Tiles {
	private String path;
	
	public Wall() {
		this.collision=true;
//		try {
//			this.path="JBubbleBobble/bin/sprites/Tiles/LevelTiles-0.png";
//		} catch ( IOException e ) {
//			System.out.println("path not found");
//		}
	}
}
