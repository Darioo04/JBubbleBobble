package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import controller.GameController;
import controller.PlayerAnimationController;
import model.GameConstants;
import model.Player;

@SuppressWarnings("deprecation")

public class PlayerView extends EntityView {
	
	enum KeyPressed {
		LEFT,RIGHT
	}
	
	private static PlayerView instance;

	private BufferedImage[] idleSprites;
	private BufferedImage[] idleSpritesSX;
	private BufferedImage[] runningSprites;
	private BufferedImage[] runningSpritesSX;
	private BufferedImage[] fallingSprites;
	private BufferedImage[] fallingSpritesSX;
	private BufferedImage[] jumpingSprites;
	private BufferedImage[] jumpingSpritesSX;
	private BufferedImage shootingSprite;
	private BufferedImage shootingSpriteSX;
	private BufferedImage[] deathSprites;
	private BufferedImage[] finalDeathAnimation;
	
	private Player player;
	private BufferedImage actualSprite;
	PlayerAnimationController playerAnimationController;
	
	public static PlayerView getInstance(int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites) {
		if (instance == null) instance = new PlayerView(numIdleSprites, numRunningSprites, numJumpingSprites, numFallingSprites);
		return instance;
	}
	
	private PlayerView (int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites) {
		super(Player.getInstance(), GameConstants.PLAYER_SIZE, numIdleSprites, numRunningSprites, numJumpingSprites, numFallingSprites);
		player = Player.getInstance();
		inizializeAnimationController();
	}

	@Override
	protected void loadSprites(int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites) {
		idleSprites = new BufferedImage[numIdleSprites];
		idleSpritesSX = new BufferedImage[numIdleSprites];
		runningSprites = new BufferedImage[numRunningSprites];
		runningSpritesSX = new BufferedImage[numRunningSprites];
		fallingSprites = new BufferedImage[numFallingSprites];
		fallingSpritesSX = new BufferedImage[numFallingSprites];
		jumpingSprites = new BufferedImage[numJumpingSprites];
		jumpingSpritesSX = new BufferedImage[numJumpingSprites];
		deathSprites = new BufferedImage[4];
		finalDeathAnimation = new BufferedImage[2];
		try {
			for (int i=0; i<numIdleSprites; i++) {
				idleSprites[i] = ImageIO.read(getClass().getResource(path + "idle-" + (i+1) + ".png"));
				idleSpritesSX[i] = ImageIO.read(getClass().getResource(path + "idle-sx-" + (i+1) + ".png"));
			}
			
			for (int i=0; i<numRunningSprites; i++) {
				runningSprites[i] = ImageIO.read(getClass().getResource(path + "running-" + (i+1) + ".png"));
				runningSpritesSX[i] = ImageIO.read(getClass().getResource(path + "running-sx-" + (i+1) + ".png"));
			}
			
			for (int i=0; i<numJumpingSprites; i++) {
				jumpingSprites[i] = ImageIO.read(getClass().getResource(path + "jumping-" + (i+1) + ".png"));
				jumpingSpritesSX[i] = ImageIO.read(getClass().getResource(path + "jumping-sx-" + (i+1) + ".png"));
			}
			
			for (int i=0; i<numFallingSprites; i++) {
				fallingSprites[i] = ImageIO.read(getClass().getResource(path + "falling-" + (i+1) +".png"));
				fallingSpritesSX[i] = ImageIO.read(getClass().getResource(path + "falling-sx-" + (i+1) +".png"));
			}
			
			for (int i=0; i<4; i++) {
				deathSprites[i] = ImageIO.read(getClass().getResource(path + "death" + (i+1) + ".png"));
			}
			
			shootingSprite = ImageIO.read(getClass().getResource(path + "shooting.png"));
            shootingSpriteSX = ImageIO.read(getClass().getResource(path + "shootingsx.png"));
            
            finalDeathAnimation[0] = ImageIO.read(getClass().getResource(path + "death5.png"));
            finalDeathAnimation[1] = ImageIO.read(getClass().getResource(path + "death6.png"));

            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void loadDefaultSprite() {
		defaultSprite = idleSprites[0];
	}
	
	public void inizializeAnimationController() {
		playerAnimationController = new PlayerAnimationController.Builder()
				.setPlayer(player)
				.setActualSprite(actualSprite)
				.setIdleSprites(idleSprites)
				.setIdleSpritesSX(idleSpritesSX)
				.setRunningSprites(runningSprites)
				.setRunningSpritesSX(runningSpritesSX)
				.setFallingSprites(fallingSprites)
				.setFallingSpritesSX(fallingSpritesSX)
				.setJumpingSprites(jumpingSprites)
				.setJumpingSpritesSX(jumpingSpritesSX)
				.setShootingSprite(shootingSprite)
				.setShootingSpriteSX(shootingSpriteSX)
				.setDeathSprites(deathSprites)
				.setFinalDeathAnimation(finalDeathAnimation)
				.build();
	}
	
	public void drawHitbox(Graphics2D g) {		//per debug, viene chiamata nel gamePanel
		g.setColor(Color.BLUE);
		g.drawRect(player.getHitboxX(), player.getHitboxY(), player.getHitboxWidth(), player.getHitboxHeight());
	}
	

	
	@Override
	public void update(Observable o,Object arg) {
		super.update(o, arg);
		if (o instanceof Player && arg instanceof BufferedImage) {
			actualSprite = (BufferedImage) arg;
			resizeIcon(actualSprite);
			setIcon(resizedIcon);
		}
	}
	
}
