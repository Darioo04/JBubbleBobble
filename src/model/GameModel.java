package model;

import java.io.IOException;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class GameModel extends Observable {
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private int numSaveSlot;
	private int highScore;
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
	public int getHighScore() {
		return highScore;
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
	public void setNickname(String nickName) {
		this.nickName=nickName;
		update();
	}
	public void setHighScore(int newScore) {
		this.highScore = (newScore>highScore) ? newScore : highScore;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	

}
