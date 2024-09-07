package controller;

import java.awt.image.BufferedImage;

import model.Bubble;

public class BubbleAnimationController {
	
	private Bubble bubble;
	private BufferedImage actualSprite;
	private static  BufferedImage[] explodedSprites;
	private BufferedImage[] floatingSprites;
	
	private BubbleAnimationController(Builder builder) {
		this.bubble = builder.bubble;
		this.actualSprite = builder.actualSprite;
		this.floatingSprites = builder.floatingSprites;
		if (explodedSprites==null) 	explodedSprites = builder.explodedSprites;
	}
	
	public void updateAnimation(int animationCycle) {
		if (bubble.isFloating()) {
			actualSprite = getSprite(floatingSprites, animationCycle);
		}
		else if (bubble.isExploded()) {
			actualSprite = getSprite(explodedSprites, animationCycle);
		}
		bubble.update(actualSprite);
	}
	
	private BufferedImage getSprite(BufferedImage[] sprites, int animationCycle) {
		return (sprites.length>0) ? sprites[animationCycle % sprites.length] : actualSprite;
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
