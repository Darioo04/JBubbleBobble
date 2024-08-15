package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import controller.PlayerAnimationController;
import model.GameConstants;
import model.Player;


public class PlayerView extends EntityView {
	
	enum KeyPressed {
		LEFT,RIGHT
	}
	
	private static PlayerView instance;
	private PlayerAnimationController playerAnimationController;
	
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
	private BufferedImage shooting;
	private BufferedImage shootingsx;
	private KeyPressed lastKeyPressed;
	private Player player;
	private BufferedImage actualSprite;
	
	
	public static PlayerView getInstance() {
		if (instance == null) instance = new PlayerView();
		return instance;
	}
	
	private PlayerView () {
		super(Player.getInstance(), GameConstants.PLAYER_SIZE);
		player = Player.getInstance();
		this.playerAnimationController = PlayerAnimationController.getInstance();
		this.lastKeyPressed = KeyPressed.RIGHT;
	}

	@Override
	protected void loadSprites() {
		try {
			
			idle1 = ImageIO.read(getClass().getResource(path + "idle-1.png"));
			idle2 = ImageIO.read(getClass().getResource(path + "idle-2.png"));
			running1 = ImageIO.read(getClass().getResource(path + "running-1.png"));
			running2 = ImageIO.read(getClass().getResource(path + "running-2.png"));
			jumping1 = ImageIO.read(getClass().getResource(path + "jumping-1.png"));
			jumping2 = ImageIO.read(getClass().getResource(path + "jumping-2.png"));
			falling1 = ImageIO.read(getClass().getResource(path + "falling-1.png"));
			falling2 = ImageIO.read(getClass().getResource(path + "falling-2.png"));
			shooting = ImageIO.read(getClass().getResource(path + "shooting.png"));
			
            idle1sx = ImageIO.read(getClass().getResource(path + "idle-sx-1.png"));
            idle2sx = ImageIO.read(getClass().getResource(path + "idle-sx-2.png"));
            running1sx = ImageIO.read(getClass().getResource(path + "running-sx-1.png"));
            running2sx = ImageIO.read(getClass().getResource(path + "running-sx-2.png"));
            jumping1sx = ImageIO.read(getClass().getResource(path + "jumping-sx-1.png"));
            jumping2sx = ImageIO.read(getClass().getResource(path + "jumping-sx-2.png"));
            falling1sx = ImageIO.read(getClass().getResource(path + "falling-sx-1.png"));
            falling2sx = ImageIO.read(getClass().getResource(path + "falling-sx-2.png"));
            shootingsx = ImageIO.read(getClass().getResource(path + "shootingsx.png"));
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void loadDefaultSprite() {
		defaultSprite = idle1;
	}
	
	public void updateAnimation(int animationCycle) {
		if(!player.isMoving() && !player.isFalling()) {				//se il player non si sta muovendo scegli l'idle in base all'ultima direzione
			switch (lastKeyPressed) {
				case RIGHT -> {
					if (player.isSpacePressed()) actualSprite = shooting;
					else {
						actualSprite = (actualSprite == idle1) ? idle2 : idle1;
					}
				}
				case LEFT -> {
					if (player.isSpacePressed()) actualSprite = shootingsx;
					else {
						actualSprite = (actualSprite == idle1sx) ? idle2sx : idle1sx;
					}
				}
			}
		}
		else if(player.isFalling()) {
			switch (lastKeyPressed) {
				case RIGHT -> {
					actualSprite = (actualSprite == falling1) ? falling2 : falling1;
				}
				case LEFT -> {
					actualSprite = (actualSprite == falling1sx) ? falling2sx : falling1sx;
	                
				}
			}
		}
		
		else if (player.isJumping()) {
			switch (lastKeyPressed) {
				case RIGHT -> {
					actualSprite = (actualSprite == jumping1) ? jumping2 : jumping1;
				}
				case LEFT -> {
					actualSprite = (actualSprite == jumping1sx) ? jumping2sx : jumping1sx;
				}
			};
		}
		else {
			if (player.isRightPressed()) {
				lastKeyPressed = KeyPressed.RIGHT;
				if (player.isSpacePressed()) actualSprite = shooting;
				else {
						actualSprite = switch (animationCycle % 3) {  // Ci sono 3 fasi nell'animazione: idle2, running1, running2
	        			case 0 -> running1;
	        			case 1 -> running2;
	        			case 2 -> idle2;
	        			default -> null;
						};
				}
					
				
				
			}
			
            if (player.isLeftPressed()) {
            	lastKeyPressed = KeyPressed.LEFT;
            	
            	if (player.isSpacePressed()) actualSprite = shootingsx;
            	else {
	            		actualSprite = switch (animationCycle % 3) {
	            		case 0 -> running1sx;
	            		case 1 -> running2sx;
	            		case 2 -> idle2sx;
	            		default -> null;	
	            		};
            	}
	            	
            	
            }
            
//            if (player.isSpacePressed()) {
//            	actualSprite = (player.isRightPressed()) ? shooting : shootingsx;
//            }
		}
		
		this.resizeIcon(actualSprite);
        this.setIcon(resizedIcon);
	}
	
	public void drawHitbox(Graphics2D g) {		//per debug, viene chiamata nel gamePanel
		g.setColor(Color.BLUE);
		g.drawRect(player.getHitboxX(), player.getHitboxY(), player.getHitboxWidth(), player.getHitboxHeight());
	}
	
	@Override
	public void update(Observable o,Object arg) {
		if (o instanceof Player) {
			super.update(o, arg);
//			actualSprite = playerAnimationController.updateAnimation();
		}
	}
	
}
