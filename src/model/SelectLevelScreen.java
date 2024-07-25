package model;

import view.SelectLevelPanel;

public class SelectLevelScreen extends StateScreen {
	private static SelectLevelScreen instance;
	
	public static SelectLevelScreen getInstance() {
		if (instance==null) instance = new SelectLevelScreen();
		return instance;
	}
	
	private SelectLevelScreen() {
		this.setFileName("levels-");
		this.setNumOptions(24);
		this.loadScreens();
		this.setStateScreenView(SelectLevelPanel.getInstance());
	}
}
