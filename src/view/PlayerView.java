package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Player;


public class PlayerView extends EntityView {
	private static PlayerView instance;
	private BufferedImage idle1;
	private BufferedImage idle2;
	private BufferedImage running1;
	private BufferedImage running2;
	private BufferedImage jumping1;
	private BufferedImage jumping2;
	private BufferedImage falling1;
	private BufferedImage falling2;
	
	
	public static PlayerView getInstance() {
		if (instance == null) instance = new PlayerView();
		return instance;
	}
	
	private PlayerView () {
		super(Player.getInstance());
	}

	@Override
	protected void loadSprites() {
		try {
			this.idle1 = ImageIO.read(getClass().getResource(path + "Bub-idle-1.png"));
			this.idle2 = ImageIO.read(getClass().getResource(path + "Bub-idle-2.png"));
			this.running1 = ImageIO.read(getClass().getResource(path + "Bub-running-1.png"));
			this.running2 = ImageIO.read(getClass().getResource(path + "Bub-running-2.png"));
			this.jumping1 = ImageIO.read(getClass().getResource(path + "Bub-jumping-1.png"));
			this.jumping2 = ImageIO.read(getClass().getResource(path + "Bub-jumping-2.png"));
			this.falling1 = ImageIO.read(getClass().getResource(path + "Bub-falling-1.png"));
			this.falling2 = ImageIO.read(getClass().getResource(path + "Bub-falling-2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void loadDefaultSprite() {
		try {
			this.defaultSprite = ImageIO.read(getClass().getResource(path + "Bub-idle-1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateAnimation() {
		
	}

	
}
