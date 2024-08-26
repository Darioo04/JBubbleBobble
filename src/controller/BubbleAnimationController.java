package controller;

import java.awt.image.BufferedImage;

import model.Bubble;

public class BubbleAnimationController {
	
	private Bubble bubble;
	private BufferedImage actualSprite;
	private BufferedImage[] explodedSprites;
	private BufferedImage[] floatingSprites;
	
	public BubbleAnimationController(Builder builder) {
		this.bubble = builder.bubble;
		this.actualSprite = builder.actualSprite;
		this.explodedSprites = builder.explodedSprites;
		this.floatingSprites = builder.floatingSprites;
	}
	
	public void updateAnimation(int animationCycle) {
		if (bubble.isFloating()) {
			actualSprite = floatingSprites[animationCycle % floatingSprites.length];
		}
		else if (bubble.isExploded()) {
			actualSprite = explodedSprites[animationCycle % explodedSprites.length];
		}
		
		bubble.update(actualSprite);
	}
	
	public static class Builder {
		private Bubble bubble;
		private BufferedImage actualSprite;
		private BufferedImage[] explodedSprites;
		private BufferedImage[] floatingSprites;
		
		public Builder setBubble(Bubble bubble) {
			this.bubble = bubble;
			return (this);
		}
		
		public Builder setActualSprite(BufferedImage actualSprite) {
			this.actualSprite = actualSprite;
			return (this);
		}
		
		public Builder setExplodedSprites(BufferedImage[] explodedSprites) {
			this.explodedSprites = explodedSprites;
			return (this);
		}
		
		public Builder setFloatingSprites(BufferedImage[] floatingSprites) {
			this.floatingSprites = floatingSprites;
			return (this);
		}
		
		public BubbleAnimationController build() {
			return new BubbleAnimationController(this);
		}
		
		
	}
}
