package model;

import java.util.Observable;

public class GameModel extends Observable {
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private String nickName;
	
	private static GameModel instance;
	
	public static GameModel getInstance() {
		if (instance == null) instance = new GameModel();
		return instance;
	}
	
	private GameModel() {
		
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public int getGamesLost() {
		return gamesLost;
	}
	public String getNickName() {
		return nickName;
	}
	
	public void increaseGamesPlayed() {
		gamesPlayed+=1;
		update();
	}
	public void increaseGamesWon() {
		gamesWon+=1;
		update();
	}
	public void increaseGamesLost() {
		gamesLost+=1;
		update();
	}
	public void setNickName(String nickName) {
		this.nickName=nickName;
		update();
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
}
