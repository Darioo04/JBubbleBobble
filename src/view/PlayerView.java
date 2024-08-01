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
	private BufferedImage idle1sx;
	private BufferedImage idle2sx;
	private BufferedImage running1sx;
	private BufferedImage running2sx;
	private BufferedImage jumping1sx;
	private BufferedImage jumping2sx;
	private BufferedImage falling1sx;
	private BufferedImage falling2sx;
	private String lastKeyPressed = "right";
	private Player player;
	private BufferedImage actualSprite;
	
	
	public static PlayerView getInstance() {
		if (instance == null) instance = new PlayerView();
		return instance;
	}
	
	private PlayerView () {
		super(Player.getInstance());
		player = Player.getInstance();
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
			
            this.idle1sx = ImageIO.read(getClass().getResource(path + "Bub-idle-sx-1.png"));
            this.idle2sx = ImageIO.read(getClass().getResource(path + "Bub-idle-sx-2.png"));
            this.running1sx = ImageIO.read(getClass().getResource(path + "Bub-running-sx-1.png"));
            this.running2sx = ImageIO.read(getClass().getResource(path + "Bub-running-sx-2.png"));
            this.jumping1sx = ImageIO.read(getClass().getResource(path + "Bub-jumping-sx-1.png"));
            this.jumping2sx = ImageIO.read(getClass().getResource(path + "Bub-jumping-sx-2.png"));
            this.falling1sx = ImageIO.read(getClass().getResource(path + "Bub-falling-sx-1.png"));
            this.falling2sx = ImageIO.read(getClass().getResource(path + "Bub-falling-sx-2.png"));
            
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
		
		if(!player.isMoving()) {				//se il player non si sta muovendo scegli l'idle in base all'ultima direzione
			if (lastKeyPressed == "right") { 
				if(actualSprite == idle1) {
	                actualSprite = idle2;
	            } else {
	                actualSprite = idle1;
	            }
			}
			
			if (lastKeyPressed == "left") {
				if(actualSprite == idle1sx) {
                    actualSprite = idle2sx;
                } else {
                    actualSprite = idle1sx;
                }
			}
		}
		else {
			if (player.isRightPressed()) {
				lastKeyPressed = "right";
				
				if(actualSprite == idle1 || actualSprite == idle2) {
					actualSprite = running1;
				}
				if(actualSprite == running1) {
					actualSprite = running2;
				}
				else {
					actualSprite = idle2;
				}
			}
			
            if (player.isLeftPressed()) {
            	lastKeyPressed = "left";
            	
            	if(actualSprite == idle1sx || actualSprite == idle2sx) {
                    actualSprite = running1sx;
                }
                if(actualSprite == running1sx) {
                    actualSprite = running2sx;
                }
                else {
                    actualSprite = idle2sx;  
                }
            }
		}
		
		this.resizeIcon(actualSprite);
        this.setIcon(resizedIcon);
	}

	
}
