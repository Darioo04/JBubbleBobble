package model;

public enum PowerUpType {
	PINK_CANDY(100),
	BLUE_CANDY(100),
	YELLOW_CANDY(100),
	SHOES(100),
	CLOCK(200),
	DYNAMITE(200),
	CHACK_HEART(3000),
	CRYSTAL_RING(1000),
	AMETIST_RING(1000),
	RUBY_RING(1000),
	CROSS_OF_THUNDER(3000),
	BLUE_LAMP(2000);
	
	private int score;
	
	PowerUpType(int score) {
		this.score=score;
	}
	
	public int getScore() {
		return score;
	}
	
	
}
