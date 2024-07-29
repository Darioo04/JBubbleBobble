package model;

import view.PauseScreenView;

public class PauseScreen extends StateScreen {
	private static PauseScreen instance;
	
	public static PauseScreen getInstance() {
		if (instance==null) instance = new PauseScreen();
		return instance;
	}
	
	private PauseScreen() {
		super(GameState.PAUSE);
		this.loadScreens();
		this.setStateScreenView(PauseScreenView.getInstance());
	}
}
