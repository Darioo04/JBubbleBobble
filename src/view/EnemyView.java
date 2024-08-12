package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.GameController;
import model.Enemy;
import model.GameConstants;

public class EnemyView extends EntityView {
	
	private BufferedImage idle1;
	private BufferedImage idle2;
	private BufferedImage running1;
	private BufferedImage running2;
	private BufferedImage jumping1;
	private BufferedImage jumping2;
	private BufferedImage falling1;
	private BufferedImage falling2;
	private BufferedImage idle1sx;
	private BufferedImage idle2sx;
	private BufferedImage running1sx;
	private BufferedImage running2sx;
	private BufferedImage jumping1sx;
	private BufferedImage jumping2sx;
	private BufferedImage falling1sx;
	private BufferedImage falling2sx;
	private BufferedImage[] deathAnimations;
	private BufferedImage death;
	
	private BufferedImage actualSprite;
	
	public EnemyView(Enemy enemy) {
		super(enemy, GameConstants.TILE_SIZE);
	}

	@Override
	protected void loadSprites() {
		try {
			this.idle1 = ImageIO.read(getClass().getResource(path+"idle1.png"));
			this.idle1sx = ImageIO.read(getClass().getResource(path+"idle1sx.png"));
//			this.idle2 = ImageIO.read(getClass().getResource(path + "idle2.png"));
//			this.idle2sx = ImageIO.read(getClass().getResource(path + "idle2sx.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void loadDefaultSprite() {
			this.defaultSprite = idle1;	
	}
	
	public BufferedImage getSprite() {
		return actualSprite;
	}
	
}
