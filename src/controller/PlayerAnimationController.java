package controller;

import java.awt.image.BufferedImage;

import model.Player;

public class PlayerAnimationController {
	
	enum LastKeyPressed {
		LEFT,RIGHT
	}
	
	private static PlayerAnimationController instance;
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
	
	private LastKeyPressed lastKeyPressed = LastKeyPressed.RIGHT;
	
	
	public static PlayerAnimationController getInstance(Builder builder) {
		if (instance == null) instance = new PlayerAnimationController(builder);
		return instance;
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
        if (!player.isMoving() && !player.isFalling()) {
        	
        	if (player.isSpacePressed()) {
                actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? shootingSprite : shootingSpriteSX;
            } else {
                actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? getIdleSprite(idleSprites) : getIdleSprite(idleSpritesSX);
            }
            
        } 
        else if (player.isFalling()) {
        	
        	actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? getFallingSprite(fallingSprites) : getFallingSprite(fallingSpritesSX);
            
        } 
        else if (player.isJumping()) {
        	
        	actualSprite = (lastKeyPressed == LastKeyPressed.RIGHT) ? getJumpingSprite(jumpingSprites) : getJumpingSprite(jumpingSpritesSX);
            
        } 
        else {
        	
        	if (player.isRightPressed()) {
        		
                lastKeyPressed = LastKeyPressed.RIGHT;
                actualSprite = (player.isSpacePressed()) ? shootingSprite : getRunningSprite(runningSprites, animationCycle);
                
            } else if (player.isLeftPressed()) {
            	
                lastKeyPressed = LastKeyPressed.LEFT;
                actualSprite = (player.isSpacePressed()) ? shootingSpriteSX : getRunningSprite(runningSpritesSX, animationCycle);
                
            }
            
        } 
        
        if (player.isDead()) {
        	actualSprite = deathSprites[animationCycle % 4];
        	if (actualSprite == deathSprites[3]) {
        		actualSprite = (actualSprite ==finalDeathAnimation[0]) ? finalDeathAnimation[1] : finalDeathAnimation[0];
//        		if (actualSprite == finalDeathAnimation[1]) ;
        	}
        }
        
    	player.update(actualSprite);
    }

    private BufferedImage getIdleSprite(BufferedImage[] idle) {
        return (actualSprite == idle[0]) ? idle[1] : idle[0];
    }

    private BufferedImage getRunningSprite(BufferedImage[] running, int animationCycle) {
        return switch (animationCycle % 3) {
            case 0 -> running[0];
            case 1 -> running[1];
            case 2 -> running[0];
            default -> null;
        };
    }

    private BufferedImage getFallingSprite(BufferedImage[] falling) {
        return (actualSprite == falling[0]) ? falling[1] : falling[0];
    }

    private BufferedImage getJumpingSprite(BufferedImage[] jumping) {
        return (actualSprite == jumping[0]) ? jumping[1] : jumping[0];
    }
	
//	public void updateAnimation(int animationCycle) {
//		if(!player.isMoving() && !player.isFalling()) {				//se il player non si sta muovendo scegli l'idle in base all'ultima direzione
//			switch (lastKeyPressed) {
//				case RIGHT -> {
//					if (player.isSpacePressed()) {
//						actualSprite = shootingSprite;
//						player.update(actualSprite);
//					}
//					else {
//						actualSprite = (actualSprite == idleSprites[0]) ? idleSprites[1] : idleSprites[0];
//						player.update(actualSprite);						
//					}
//				}
//				case LEFT -> {
//					if (player.isSpacePressed()) {
//						actualSprite = shootingSpriteSX;
//						player.update(actualSprite);
//					}
//					else {
//						actualSprite = (actualSprite == idleSpritesSX[0]) ? idleSpritesSX[1] : idleSpritesSX[0];
//						player.update(actualSprite);
//					}
//				}
//			}
//		}
//		else if(player.isFalling()) {
//			switch (lastKeyPressed) {
//				case RIGHT -> {
//					actualSprite = (actualSprite == fallingSprites[0]) ? fallingSprites[1] : fallingSprites[0];
//					player.update(actualSprite);
//				}
//				case LEFT -> {
//					actualSprite = (actualSprite == fallingSpritesSX[0]) ? fallingSpritesSX[1] : fallingSpritesSX[0];
//					player.update(actualSprite);	           
//				}
//			}
//		}
//		
//		else if (player.isJumping()) {
//			switch (lastKeyPressed) {
//				case RIGHT -> {
//					actualSprite = (actualSprite == jumpingSprites[0]) ? jumpingSprites[1] : jumpingSprites[0];
//					player.update(actualSprite);
//
//				}
//				case LEFT -> {
//					actualSprite = (actualSprite == jumpingSpritesSX[0]) ? jumpingSpritesSX[1] : jumpingSpritesSX[0];
//					player.update(actualSprite);
//				}
//			};
//		}
//		else {
//			if (player.isRightPressed()) {
//				lastKeyPressed = LastKeyPressed.RIGHT;
//				if (player.isSpacePressed()) {
//					actualSprite = shootingSprite;
//					player.update(actualSprite);
//				}
//				else {
//						actualSprite = switch (animationCycle % 3) {  // Ci sono 3 fasi nell'animazione: idle2, running1, running2
//	        				case 0 -> runningSprites[0];
//	        				case 1 -> runningSprites[1];
//	        				case 2 -> idleSprites[1];
//	        				default -> null;
//						};
//						player.update(actualSprite);
//				}
//					
//				
//				
//			}
//			
//            if (player.isLeftPressed()) {
//            	lastKeyPressed = LastKeyPressed.LEFT;
//            	
//            	if (player.isSpacePressed()) {
//            		actualSprite = shootingSpriteSX;
//					player.update(actualSprite);
//            	}
//            	else {
//	            		actualSprite = switch (animationCycle % 3) {
//	            			case 0 -> runningSpritesSX[0];
//	            			case 1 -> runningSpritesSX[1];
//	            			case 2 -> idleSpritesSX[1];
//	            			default -> null;	
//	            		};
//						player.update(actualSprite);
//            	}
//	            	
//            	
//            }  
////            if (player.isSpacePressed()) {
////            	actualSprite = (player.isRightPressed()) ? shooting : shootingsx;
////            }
//		}
//	}
	
	
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
		
		public PlayerAnimationController builder() {
			return PlayerAnimationController.getInstance(this);
		}
	}
}
