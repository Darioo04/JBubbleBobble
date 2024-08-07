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
	
	private BufferedImage actualSprite;
	
	public EnemyView(Enemy enemy) {
		super(enemy, GameConstants.TILE_SIZE);
	}

	@Override
	protected void loadSprites() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadDefaultSprite() {
		try {
			this.defaultSprite = ImageIO.read(getClass().getResource(path + "idle-1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getSprite() {
		return actualSprite;
	}
	
}
