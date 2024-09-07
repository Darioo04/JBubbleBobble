package model;

import java.util.Observable;

@SuppressWarnings("deprecation")

public class GameModel extends Observable {
	
	private boolean firstTimePlaying;
	private long[] topScores = new long[3];
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private String playerName;
	
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
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
		update();
	}
	
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
		update();
	}
	
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
		update();
	}

	public void increaseGamesPlayed() {
		gamesPlayed++;
		update();
	}
	public void increaseGamesWon() {
		gamesWon++;
		update();
	}
	public void increaseGamesLost() {
		gamesLost++;
		update();
	}
	public void setPlayerName(String playerName) {
		this.playerName=playerName;
		update();
	}
	
	public void setTopScores(long[] topScores) {
		this.topScores = topScores;
		update();
	}
	
	public long[] getTopScores() {
		return topScores;
	}
	
	public void setFirstTimePlaying(boolean firstTimePlaying) {
		this.firstTimePlaying = firstTimePlaying;
		update();
	}
	
	public boolean getFirstTimePlaying() {
		return firstTimePlaying;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	

}
