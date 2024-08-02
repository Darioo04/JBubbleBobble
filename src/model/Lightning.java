package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lightning {
	private static Lightning instance;
	private final int DAMAGE=900;
	private BufferedImage[] sprites;
	public static Lightning getInstance() {
		if (instance==null) instance = new Lightning();
		return instance;
	}
	
	private Lightning() {
		sprites = new BufferedImage[3];
		try {
			for (int i=0; i<sprites.length; i++) {
				sprites[i] = ImageIO.read(getClass().getResource("/sprites/Obj/lightning-"+i+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
