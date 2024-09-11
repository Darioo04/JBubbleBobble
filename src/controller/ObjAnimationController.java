package controller;

import java.awt.image.BufferedImage;

import model.ObjModel;

public class ObjAnimationController {
	/*
	 * classe che si occupa di aggiornare le animazioni degli oggetti di gioco 
	 * di tipo ObjModel
	 * implementa il builder pattern
	 */
	private ObjModel obj;
	private BufferedImage actualSprite;
	private BufferedImage[] idleSprites;
	
	private ObjAnimationController(Builder builder) {
		this.obj = builder.obj;
		this.actualSprite = builder.actualSprite;
		this.idleSprites = builder.idleSprites;
	}
	
	public void updateAnimation(int animationCycle) {
		actualSprite = idleSprites[animationCycle % idleSprites.length];
		obj.update(actualSprite);
	}
	
	public static class Builder {
		private ObjModel obj;
		private BufferedImage actualSprite;
		private BufferedImage[] idleSprites;
		
		public Builder setObj(ObjModel obj) {
			this.obj=obj;
			return this;
		}
		
		public Builder setActualSprite(BufferedImage actualSprite) {
			this.actualSprite = actualSprite;
			return this;
		}
		
		public Builder setIdleSprites(BufferedImage[] idleSprites) {
			this.idleSprites = idleSprites;
			return this;
		}
		
		public ObjAnimationController build() {
			return new ObjAnimationController(this);
		}
	}
}
