package model;

import view.SelectLevelView;

public class SelectProfileScreen extends StateScreen {
	private static SelectProfileScreen instance;
	
	public static SelectProfileScreen getInstance() {
		if (instance==null) instance = new SelectProfileScreen();
		return instance;
	}
	
	private SelectProfileScreen() {
		this.setGameState(GameState.SELECT_PROFILE);
		this.setFileName(getGameState().getPath());
		this.setNumOptions(getGameState().getNumScreens());
		this.loadScreens();
//		this.setStateScreenPanel(SelectProfilePanel.getInstance());
	}
}
