package model;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	protected long score;
	private static Player instance;
	
	private Player() {
		super(50, 50, "Player");
	}
	
	public static Player getInstance() {
		if (instance==null) instance= new Player();
		return instance;
	}
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score+=score;
		setChanged();
		notifyObservers();
	}

}
