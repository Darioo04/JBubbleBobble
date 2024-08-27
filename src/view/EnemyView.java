package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import controller.EnemyAnimationController;
import controller.GameController;
import model.Enemy;
import model.GameConstants;

@SuppressWarnings("deprecation")

public class EnemyView extends EntityView {
	
	private BufferedImage[] idleSprites;
	private BufferedImage[] runningSprites;
	private BufferedImage[] jumpingSprites;
	private BufferedImage[] fallingSprites;
	private BufferedImage[] idleSpritesSX;
	private BufferedImage[] runningSpritesSX;
	private BufferedImage[] jumpingSpritesSX;
	private BufferedImage[] fallingSpritesSX;
	private BufferedImage[] deathSprites;
	private BufferedImage[] inBubbleSprites;
	private BufferedImage finalDeathAnimation;
	
	private BufferedImage actualSprite;
	
	public EnemyView(Enemy enemy, int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites) {
		super(enemy, GameConstants.TILE_SIZE, numIdleSprites, numRunningSprites, numJumpingSprites, numFallingSprites);
	}

	@Override
	protected void loadSprites(int numIdleSprites, int numRunningSprites, int numJumpingSprites, int numFallingSprites) {
		inBubbleSprites = new BufferedImage[3];
		deathSprites = new BufferedImage[4];
		idleSprites = new BufferedImage[numIdleSprites];
		idleSpritesSX = new BufferedImage[numIdleSprites];
		runningSprites = new BufferedImage[numRunningSprites];
		runningSpritesSX = new BufferedImage[numRunningSprites];
		jumpingSprites = new BufferedImage[numJumpingSprites];
		jumpingSpritesSX = new BufferedImage[numJumpingSprites];
		fallingSprites = new BufferedImage[numFallingSprites];
		fallingSpritesSX = new BufferedImage[numFallingSprites];
		
		try {
			for (int i=0; i<numIdleSprites; i++) {
				idleSprites[i] = ImageIO.read(getClass().getResource(path + "idle-" + (i+1) + ".png"));
				try {
					idleSpritesSX[i] = ImageIO.read(getClass().getResource(path + "idle-sx-" + (i+1) + ".png"));
				} catch (Exception e) {
					idleSpritesSX[i] = null;
				}
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
				fallingSprites[i] = ImageIO.read(getClass().getResource(path + "falling-" + (i+1) + ".png"));
				fallingSpritesSX[i] = ImageIO.read(getClass().getResource(path + "falling-sx-" + (i+1) + ".png"));
			}

			for (int i=0; i<3; i++) {
				inBubbleSprites[i] = ImageIO.read(getClass().getResource(path + "inBubble-" + (i+1) + ".png"));
			}
			
			for (int i=0; i<4; i++) {
				deathSprites[i] = ImageIO.read(getClass().getResource(path + "death-" + (i+1) + ".png"));
			}
			
			finalDeathAnimation = ImageIO.read(getClass().getResource(path + "death.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		inizializeAnimationController(); // dopo aver caricato tutte le sprite le carico nell'animation controller
	}

	@Override
	protected void loadDefaultSprite() {
		this.defaultSprite = idleSprites[0];	
	}
	
	public void inizializeAnimationController() {
		EnemyAnimationController enemyAnimationController = new EnemyAnimationController.Builder()
				.setEnemy((Enemy)entity)
				.setActualSprite(actualSprite)
				.setIdleSprites(idleSprites)
				.setIdleSpritesSX(idleSpritesSX)
				.setRunningSprites(runningSprites)
				.setRunningSpritesSX(runningSpritesSX)
				.setJumpingSprites(jumpingSprites)
				.setJumpingSpritesSX(jumpingSpritesSX)
				.setDeathSprites(deathSprites)
				.setInBubbleSprites(inBubbleSprites)
				.setFinalDeathSprite(finalDeathAnimation)
				.build();
		GameController.getInstance().addEnemyAnimationController(enemyAnimationController);
	}
	
	@Override
	public void update(Observable o,Object arg) {
		super.update(o, arg);
		if (o instanceof Enemy && arg instanceof BufferedImage) {
			actualSprite = (BufferedImage) arg;
			resizeIcon(actualSprite);
			setIcon(resizedIcon);
		}
	}
	
}
