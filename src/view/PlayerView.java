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
	
	public static PlayerView getInstance(int numIdleSprites, int numRunningSprites, int numJumpingSprites) {
		if (instance == null) instance = new PlayerView(numIdleSprites, numRunningSprites, numJumpingSprites);
		return instance;
	}
	
	private PlayerView (int numIdleSprites, int numRunningSprites, int numJumpingSprites) {
		super(Player.getInstance(), GameConstants.PLAYER_SIZE, numIdleSprites, numRunningSprites, numJumpingSprites);
		player = Player.getInstance();
		inizializeAnimationController();
	}

	@Override
	protected void loadSprites(int numIdleSprites, int numRunningSprites, int numJumpingSprites) {
		idleSprites = new BufferedImage[numIdleSprites];
		idleSpritesSX = new BufferedImage[numIdleSprites];
		runningSprites = new BufferedImage[numRunningSprites];
		runningSpritesSX = new BufferedImage[numRunningSprites];
		fallingSprites = new BufferedImage[2];
		fallingSpritesSX = new BufferedImage[2];
		jumpingSprites = new BufferedImage[numJumpingSprites];
		jumpingSpritesSX = new BufferedImage[numJumpingSprites];
		deathSprites = new BufferedImage[4];
		finalDeathAnimation = new BufferedImage[2];
		try {
			
			idleSprites[0] = ImageIO.read(getClass().getResource(path + "idle-1.png"));
			idleSprites[1] = ImageIO.read(getClass().getResource(path + "idle-2.png"));
			runningSprites[0] = ImageIO.read(getClass().getResource(path + "running-1.png"));
			runningSprites[1] = ImageIO.read(getClass().getResource(path + "running-2.png"));
			jumpingSprites[0] = ImageIO.read(getClass().getResource(path + "jumping-1.png"));
			jumpingSprites[1] = ImageIO.read(getClass().getResource(path + "jumping-2.png"));
			fallingSprites[0] = ImageIO.read(getClass().getResource(path + "falling-1.png"));
			fallingSprites[1] = ImageIO.read(getClass().getResource(path + "falling-2.png"));
			shootingSprite = ImageIO.read(getClass().getResource(path + "shooting.png"));
			
            idleSpritesSX[0] = ImageIO.read(getClass().getResource(path + "idle-sx-1.png"));
            idleSpritesSX[1] = ImageIO.read(getClass().getResource(path + "idle-sx-2.png"));
            runningSpritesSX[0] = ImageIO.read(getClass().getResource(path + "running-sx-1.png"));
            runningSpritesSX[1] = ImageIO.read(getClass().getResource(path + "running-sx-2.png"));
            jumpingSpritesSX[0] = ImageIO.read(getClass().getResource(path + "jumping-sx-1.png"));
            jumpingSpritesSX[1] = ImageIO.read(getClass().getResource(path + "jumping-sx-2.png"));
            fallingSpritesSX[0] = ImageIO.read(getClass().getResource(path + "falling-sx-1.png"));
            fallingSpritesSX[1] = ImageIO.read(getClass().getResource(path + "falling-sx-2.png"));
            shootingSpriteSX = ImageIO.read(getClass().getResource(path + "shootingsx.png"));
            
            for (int i=0; i<4;i++) {
            	deathSprites[i] = ImageIO.read(getClass().getResource(path + "death"+(i+1)+".png"));
            }
            
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
