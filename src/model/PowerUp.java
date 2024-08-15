package model;

import java.awt.Rectangle;

public class PowerUp {
	
	private PowerUpType powerUp;
	private int score;
	private int x;
	private int y;
	private Rectangle hitbox;
	
	public PowerUp(PowerUpType powerUp) {
		this.powerUp = powerUp;
		this.score = powerUp.getScore();
	}
	
//	public void update() {
//		Player player = Player.getInstance();
//		if (player.getHitbox().intersects(hitbox)) {
//			switch (powerUp) {
//				case PINK_CANDY -> {
//					
//				}
//				
//				case BLUE_CANDY -> {
//					
//				}
//				
//				case YELLOW_CANDY -> {
//					
//				}
//				
//				case SHOES -> {
//					
//				}
//				
//				case CLOCK -> {
//					
//				}
//				
//				case DYNAMITE -> {
//					
//				}
//				
//				case CHACK_HEART -> {
//					
//				}
//				
//				case CRYSTAL_RING -> {
//					
//				}
//				
//				case AMETIST_RING -> {
//					
//				}
//				
//				case RUBY_RING -> {
//					
//				}
//				
//				case CROSS_OF_THUNDER -> {
//					
//				}
//				
//				case BLUE_LAMP -> {
//					
//				}
//			}
//		}
//	}
}
