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
	
	private Enemy enemy;
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
	private BufferedImage[] inBubbleSprites;
	
	private BufferedImage actualSprite;
		
	public EnemyView(Enemy enemy) {
		super(enemy, GameConstants.TILE_SIZE);
		this.enemy = enemy;
	}

	@Override
	protected void loadSprites() {
		inBubbleSprites = new BufferedImage[3];
		try {
			this.idle1 = ImageIO.read(getClass().getResource(path+"idle-1.png"));
			this.idle1sx = ImageIO.read(getClass().getResource(path+"idle-1sx.png"));
//			this.idle2 = ImageIO.read(getClass().getResource(path + "idle2.png"));
//			this.idle2sx = ImageIO.read(getClass().getResource(path + "idle2sx.png"));
			for (int i=0; i<3; i++) {
				inBubbleSprites[i] = ImageIO.read(getClass().getResource(path + "inBubble-"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		inizializeAnimationController(); // dopo aver caricato tutte le sprite le carico nell'animation controller
	}

	@Override
	protected void loadDefaultSprite() {
			this.defaultSprite = idle1;	
	}
	
	public void inizializeAnimationController() {
		EnemyAnimationController enemyAnimationController = new EnemyAnimationController.Builder()
				.setEnemy(enemy)
				.setActualSprite(actualSprite)
				.setIdleSprites(new BufferedImage[] {idle1})
				.setIdleSpritesSX(new BufferedImage[] {idle1sx})
				.setRunningSprites(new BufferedImage[] {})
				.setRunningSpritesSX(new BufferedImage[] {})
				.setJumpingSprites(new BufferedImage[] {})
				.setJumpingSpritesSX(new BufferedImage[] {})
				.setDeathSprites(deathAnimations)
				.setFinalDeathAnimation(new BufferedImage[] {})
				.setInBubbleSprites(inBubbleSprites)
				.builder();
		GameController.getInstance().addEnemyAnimationController(enemyAnimationController);
	}
	
	
	
	@Override
	public void update(Observable o,Object arg) {
		super.update(o, arg);
		if (o instanceof Enemy && arg instanceof BufferedImage) {
//			actualSprite = (BufferedImage) arg;
//			resizeIcon(actualSprite);
//			setIcon(resizedIcon);
//			actualSprite = enemyAnimationController.updateAnimation();
		}
	}
	
}
