package model;

import java.util.Observable;

@SuppressWarnings("deprecation")

public class Points extends Observable {
	
	private int pointsCounter;
	private boolean canBeDeleted;
	private static final int DELAY = 120;
	private int x,y;
	private int points;
	
	public Points(int x, int y, int points) {
		this.x = x;
		this.y = y;
		this.points = points;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPoints() {
		return points;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setCanBeDeleted(boolean canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	
	public boolean canBeDeleted() {
		return canBeDeleted;
	}
	
	public void update() {
		y -= 5 * GameConstants.SCALE;
		if (pointsCounter++ >= DELAY) {
			canBeDeleted = true;
		}
		setChanged();
		notifyObservers();
	}
}
