package model;

import view.LastLevelWinScreenView;

public class LastLevelWinScreen extends StateScreen {
	private static LastLevelWinScreen instance;
	
	public static LastLevelWinScreen getInstance() {
		if (instance == null) instance = new LastLevelWinScreen();
		return instance;
	}
	
	private LastLevelWinScreen() {
		super(GameState.LAST_WIN);
		this.loadScreens();
		this.setStateScreenView(LastLevelWinScreenView.getInstance());
	}
}
