package controller;

import java.awt.image.BufferedImage;

import model.Direction;
import model.Player;

public class PlayerAnimationController {
	private static PlayerAnimationController instance;
	private Player player = Player.getInstance();
	private Direction lastKeyPressed = Direction.RIGHT;
	private BufferedImage actualSprite;
	
	
	public static PlayerAnimationController getInstance() {
		if (instance == null) instance = new PlayerAnimationController();
		return instance;
	}
	
	private PlayerAnimationController() {
		
	}
	
//	public BufferedImage updateAnimation(int animationCycle) {
//		if(!player.isMoving() && !player.isFalling()) {				//se il player non si sta muovendo scegli l'idle in base all'ultima direzione
//			switch (lastKeyPressed) {
//				case RIGHT -> {
//					if (player.isSpacePressed()) return (actualSprite = shooting);
//					else {
//						actualSprite = (actualSprite == idle1) ? idle2 : idle1;
//						return actualSprite;
//					}
//				}
//				case LEFT -> {
//					if (player.isSpacePressed()) return (actualSprite = shootingsx);
//					else {
//						actualSprite = (actualSprite == idle1sx) ? idle2sx : idle1sx;
//						return actualSprite;
//					}
//				}
//			}
//		}
//		else if(player.isFalling()) {
//			switch (lastKeyPressed) {
//				case RIGHT -> {
//					actualSprite = (actualSprite == falling1) ? falling2 : falling1;
//					return actualSprite;
//				}
//				case LEFT -> {
//					actualSprite = (actualSprite == falling1sx) ? falling2sx : falling1sx;
//					return actualSprite;
//	                
//				}
//			}
//		}
//		
//		else if (player.isJumping()) {
//			switch (lastKeyPressed) {
//				case RIGHT -> {
//					actualSprite = (actualSprite == jumping1) ? jumping2 : jumping1;
//				}
//				case LEFT -> {
//					actualSprite = (actualSprite == jumping1sx) ? jumping2sx : jumping1sx;
//				}
//			};
//		}
//		else {
//			if (player.isRightPressed()) {
//				lastKeyPressed = Direction.RIGHT;
//				if (player.isSpacePressed()) return (actualSprite = shooting);
//				else {
//						return switch (animationCycle % 3) {  // Ci sono 3 fasi nell'animazione: idle2, running1, running2
//	        			case 0 -> running1;
//	        			case 1 -> running2;
//	        			case 2 -> idle2;
//	        			default -> null;
//						};
//				}
//					
//				
//				
//			}
//			
//            if (player.isLeftPressed()) {
//            	lastKeyPressed = Direction.LEFT;
//            	
//            	if (player.isSpacePressed()) return (actualSprite = shootingsx);
//            	else {
//	            		return switch (animationCycle % 3) {
//	            		case 0 -> running1sx;
//	            		case 1 -> running2sx;
//	            		case 2 -> idle2sx;
//	            		default -> null;	
//	            		};
//            	}
//	            	
//            	
//            }
//            
////            if (player.isSpacePressed()) {
////            	actualSprite = (player.isRightPressed()) ? shooting : shootingsx;
////            }
//		}
//	}
}
