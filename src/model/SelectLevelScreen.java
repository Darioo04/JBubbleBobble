package model;

import view.SelectLevelView;

public class SelectLevelScreen extends StateScreen {
	private static SelectLevelScreen instance;
	
	public static SelectLevelScreen getInstance() {
		if (instance==null) instance = new SelectLevelScreen();
		return instance;
	}
	
	private SelectLevelScreen() {
		this.setGameState(GameState.SELECT_LEVEL);
		this.setFileName( getGameState().getPath() );
		this.setNumOptions( getGameState().getNumScreens() );
		this.loadScreens();
		this.setStateScreenView(SelectLevelView.getInstance());
	}
}
