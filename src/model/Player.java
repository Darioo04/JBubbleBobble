package model;

public class Player extends Entity {
	protected long score;
	
	public Player() {
		super(100, 50, 50);
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
