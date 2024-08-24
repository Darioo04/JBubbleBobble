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
	private BufferedImage falling1;
	private BufferedImage falling2;
	private BufferedImage[] idleSpritesSX;
	private BufferedImage[] runningSpritesSX;
	private BufferedImage[] jumpingSpritesSX;
	private BufferedImage falling1sx;
	private BufferedImage falling2sx;
	private BufferedImage[] deathSprites;
	private BufferedImage death;
	private BufferedImage[] inBubbleSprites;
	
	private BufferedImage actualSprite;
	
	public EnemyView(Enemy enemy, int numIdleSprites, int numRunningSprites, int numJumpingSprites) {
		super(enemy, GameConstants.TILE_SIZE, numIdleSprites, numRunningSprites, numJumpingSprites);
	}

	@Override
	protected void loadSprites(int numIdleSprites, int numRunningSprites, int numJumpingSprites) {
		inBubbleSprites = new BufferedImage[3];
		deathSprites = new BufferedImage[4];
		idleSprites = new BufferedImage[numIdleSprites];
		idleSpritesSX = new BufferedImage[numIdleSprites];
		runningSprites = new BufferedImage[numRunningSprites];
		runningSpritesSX = new BufferedImage[numRunningSprites];
		jumpingSprites = new BufferedImage[numJumpingSprites];
		jumpingSpritesSX = new BufferedImage[numJumpingSprites];
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

			for (int i=0; i<3; i++) {
				inBubbleSprites[i] = ImageIO.read(getClass().getResource(path + "inBubble-" + (i+1) + ".png"));
			}
			
			for (int i=0; i<4; i++) {
				deathSprites[i] = ImageIO.read(getClass().getResource(path + "death-" + (i+1) + ".png"));
			}
			this.death = ImageIO.read(getClass().getResource(path + "death.png"));
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
				.setFinalDeathAnimation(death)
				.setInBubbleSprites(inBubbleSprites)
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
