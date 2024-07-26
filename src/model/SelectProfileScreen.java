package model;

import view.SelectLevelView;

public class SelectProfileScreen extends StateScreen {
	private static SelectProfileScreen instance;
	private GameState state;
	
	public static SelectProfileScreen getInstance() {
		if (instance==null) instance = new SelectProfileScreen();
		return instance;
	}
	
	private SelectProfileScreen() {
		this.state=GameState.SELECT_PROFILE;
		this.setFileName(state.getPath());
		this.setNumOptions(state.getNumScreens());
		this.loadScreens();
//		this.setStateScreenPanel(SelectProfilePanel.getInstance());
	}
}
