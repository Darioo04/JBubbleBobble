package controller;

import java.awt.image.BufferedImage;

import model.GameState;
import model.Player;

public class PlayerAnimationController {
	
	enum LastKeyPressed {
		LEFT,RIGHT
	}
	
	private static PlayerAnimationController instance;
	private Player player;
	private static Builder builder;
	
	private BufferedImage actualSprite;
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
	
	private LastKeyPressed lastKeyPressed = LastKeyPressed.RIGHT;
	
	
	public static PlayerAnimationController getInstance() {
		if (instance == null) {
			instance = new PlayerAnimationController(builder);
		}
		return instance;
	}
	
	private static void setBuilder(Builder builder) {
		PlayerAnimationController.builder = builder;
	}
	
	private PlayerAnimationController(Builder builder) {
		this.player = builder.player;
		this.actualSprite = builder.actualSprite;
		this.idleSprites = builder.idleSprites;
		this.idleSpritesSX = builder.idleSpritesSX;
		this.runningSprites = builder.runningSprites;
		this.runningSpritesSX = builder.runningSpritesSX;
		this.fallingSprites = builder.fallingSprites;
		this.fallingSpritesSX = builder.fallingSpritesSX;
		this.jumpingSprites = builder.jumpingSprites;
		this.jumpingSpritesSX = builder.jumpingSpritesSX;
		this.shootingSprite = builder.shootingSprite;
		this.shootingSpriteSX = builder.shootingSpriteSX;
		this.deathSprites = builder.deathSprites;
		this.finalDeathAnimation = builder.finalDeathAnimation;
	}
	
    public void updateAnimation(int animationCycle) {
        if (!player.isMoving() && !player.isInAir()) {
        	
        	if (player.isSpacePressed()) {
                actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? shootingSprite : shootingSpriteSX;
            } else {
                actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? getSprite(idleSprites,animationCycle) : getSprite(idleSpritesSX,animationCycle);
            }
            
        } 
        else if (player.isJumping()) {
        	actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? getSprite(jumpingSprites,animationCycle) : getSprite(jumpingSpritesSX,animationCycle);
        } 
        else if (player.isInAir()) {
        	actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? getSprite(fallingSprites,animationCycle) : getSprite(fallingSpritesSX,animationCycle);
        } 
        
       
        else {
        	
        	if (player.isRightPressed()) {
        		
                lastKeyPressed = LastKeyPressed.RIGHT;
                actualSprite = getSprite(runningSprites, animationCycle);
                
            } else if (player.isLeftPressed()) {
            	
                lastKeyPressed = LastKeyPressed.LEFT;
                if (player.isSpacePressed()) {
                    actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? shootingSprite : shootingSpriteSX;
                }
                else actualSprite = getSprite(runningSpritesSX, animationCycle);
                
            }
            
        } 
        if (player.isSpacePressed()) {
            actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? shootingSprite : shootingSpriteSX;
        }
        
        if (player.isDead()) {
        	actualSprite = deathSprites[animationCycle % deathSprites.length];
        	if (actualSprite == deathSprites[deathSprites.length-1]) {
        		actualSprite = finalDeathAnimation[animationCycle % finalDeathAnimation.length];
        	}
        }
        
    	player.update(actualSprite);
    }

    private BufferedImage getSprite(BufferedImage[] sprites, int animationCycle) {
        return sprites[animationCycle % sprites.length];
    }
		
	public static class Builder {
		private Player player;
		private BufferedImage actualSprite;
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
		
		public Builder setPlayer(Player player) {
			this.player=player;
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
		
		public Builder setFallingSprites(BufferedImage[] fallingSprites) {
			this.fallingSprites = fallingSprites;
			return (this);
		}
		
		public Builder setFallingSpritesSX(BufferedImage[] fallingSpritesSX) {
			this.fallingSpritesSX = fallingSpritesSX;
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
		
		public Builder setShootingSprite(BufferedImage shootingSprite) {
			this.shootingSprite = shootingSprite;
			return (this);
		}
		
		public Builder setShootingSpriteSX(BufferedImage shootingSpriteSX) {
			this.shootingSpriteSX = shootingSpriteSX;
			return (this);
		}
		
		public Builder setDeathSprites(BufferedImage[] deathSprites) {
			this.deathSprites = deathSprites;
			return (this);
		}
		
		public Builder setFinalDeathAnimation(BufferedImage[] finalDeathAnimation) {
			this.finalDeathAnimation = finalDeathAnimation;
			return (this);
		}
		
		public PlayerAnimationController build() {
			PlayerAnimationController.setBuilder(this);
			return PlayerAnimationController.getInstance();
		}
	}
}
