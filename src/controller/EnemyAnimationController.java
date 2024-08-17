package controller;

import java.awt.image.BufferedImage;

import model.Enemy;

public class EnemyAnimationController {
	
	private Enemy enemy;
	private BufferedImage actualSprite;
	private BufferedImage[] idleSprites;
	private BufferedImage[] idleSpritesSX;
	private BufferedImage[] runningSprites;
	private BufferedImage[] runningSpritesSX;
	private BufferedImage[] jumpingSprites;
	private BufferedImage[] jumpingSpritesSX;
	private BufferedImage[] inBubbleSprites;
	private BufferedImage[] deathSprites;
	private BufferedImage[] finalDeathAnimation;
	
	public EnemyAnimationController(Builder builder) {
		this.enemy = builder.enemy;
		this.actualSprite = builder.actualSprite;
		this.idleSprites = builder.idleSprites;
		this.idleSpritesSX = builder.idleSpritesSX;
		this.runningSprites = builder.runningSprites;
		this.runningSpritesSX = builder.runningSpritesSX;
		this.jumpingSprites = builder.jumpingSprites;
		this.jumpingSpritesSX = builder.jumpingSpritesSX;
		this.inBubbleSprites = builder.inBubbleSprites;
		this.deathSprites = builder.deathSprites;
		this.finalDeathAnimation = builder.finalDeathAnimation;
	}
	
	public void updateAnimation(int animationCycle) {
		if (enemy.isInBubble()) {
			actualSprite = inBubbleSprites[animationCycle % inBubbleSprites.length];
			enemy.update(actualSprite);
		}
		
	}
	
	
	
	public static class Builder {
		private Enemy enemy;
		private BufferedImage actualSprite;
		private BufferedImage[] idleSprites;
		private BufferedImage[] idleSpritesSX;
		private BufferedImage[] runningSprites;
		private BufferedImage[] runningSpritesSX;
		private BufferedImage[] jumpingSprites;
		private BufferedImage[] jumpingSpritesSX;
		private BufferedImage[] inBubbleSprites;
		private BufferedImage[] deathSprites;
		private BufferedImage[] finalDeathAnimation;
		
		public Builder setEnemy(Enemy enemy) {
			this.enemy=enemy;
			return (this);
		}
		
		public Builder setActualSprite(BufferedImage actualSprite) {
			this.actualSprite = actualSprite;
			return (this);
		}
		
		public Builder setIdleSprites(BufferedImage[] idleSprites) {
			this.idleSprites=idleSprites;
			return (this);
		}
		
		public Builder setIdleSpritesSX(BufferedImage[] idleSpritesSX) {
			this.idleSpritesSX=idleSpritesSX;
			return (this);
		}
		
		public Builder setRunningSprites(BufferedImage[] runningSprites) {
			this.runningSprites=runningSprites;
			return (this);
		}
		
		public Builder setRunningSpritesSX(BufferedImage[] runningSpritesSX) {
			this.runningSpritesSX=runningSpritesSX;
			return (this);
		}
		
		public Builder setJumpingSprites(BufferedImage[] jumpingSprites) {
			this.jumpingSprites = jumpingSprites;
			return (this);
		}
		
		public Builder setJumpingSpritesSX(BufferedImage[] jumpingSpritesSX) {
			this.jumpingSpritesSX = jumpingSpritesSX;
			return (this);
		}
		
		public Builder setInBubbleSprites(BufferedImage[] inBubbleSprites) {
			this.inBubbleSprites=inBubbleSprites;
			return (this);
		}
		
		public Builder setDeathSprites(BufferedImage[] deathSprites) {
			this.deathSprites=deathSprites;
			return (this);
		}
		
		public Builder setFinalDeathAnimation(BufferedImage[] finalDeathAnimation) {
			this.finalDeathAnimation=finalDeathAnimation;
			return (this);
		}
		
		public EnemyAnimationController builder() {
			return new EnemyAnimationController(this);
		}
	}
}
