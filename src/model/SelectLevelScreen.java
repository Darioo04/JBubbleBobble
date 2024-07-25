package model;

import view.SelectLevelPanel;

public class SelectLevelScreen extends StateScreen {
	private static SelectLevelScreen instance;
	private GameState state;
	
	public static SelectLevelScreen getInstance() {
		if (instance==null) instance = new SelectLevelScreen();
		return instance;
	}
	
	private SelectLevelScreen() {
		this.state=GameState.SELECT_LEVEL;
		this.setFileName(state.getPath());
		this.setNumOptions(state.getNumScreens());
		this.loadScreens();
		this.setStateScreenPanel(SelectLevelPanel.getInstance());
	}
}
