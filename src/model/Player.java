package model;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	private long score;
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;
	
	
	private Player() {
		super(50, 50, "Player");
		this.setPath("/sprites/BubAndBob1/Bub-0.png");
	}
	
	public static Player getInstance() {
		if (instance==null) instance= new Player();
		return instance;
	}
	
	public long getScore() {
		return score;
	}
	
	public void addScore(long score) {
		this.score+=score;
		update();
	}
	
	public void resetScore() {
		this.score=0;
	}
}
