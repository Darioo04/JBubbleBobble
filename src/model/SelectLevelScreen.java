package model;

import view.SelectLevelView;

public class SelectLevelScreen extends StateScreen {
	private static SelectLevelScreen instance;
	
	public static SelectLevelScreen getInstance() {
		if (instance==null) instance = new SelectLevelScreen();
		return instance;
	}
	
	private SelectLevelScreen() {
		super(GameState.SELECT_LEVEL);
		this.loadScreens();
		this.setStateScreenView(SelectLevelView.getInstance());
	}
}
