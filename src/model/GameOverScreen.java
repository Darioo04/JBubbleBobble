package model;

import view.GameOverView;

public class GameOverScreen extends StateScreen {
	private static GameOverScreen instance;
	
	public static GameOverScreen getInstance() {
		if (instance == null) instance = new GameOverScreen();
		return instance;
	}
	
	private GameOverScreen() {
		super(GameState.GAME_OVER);
		this.loadScreens();
		this.setStateScreenView(GameOverView.getInstance());
	}
}
