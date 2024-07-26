package model;

import view.GamePanel;

public class GamingScreen extends StateScreen{
	private static GamingScreen instance;
	private Tiles[][] level;
	
	public static GamingScreen getInstance() {
		if (instance==null) instance = new GamingScreen();
		return instance;
	}
	
	private GamingScreen() {
		this.setGameState(GameState.GAME);
        this.setFileName(getGameState().getPath());
        this.setNumOptions(getGameState().getNumScreens());
//		this.loadScreens();
		this.setStateScreenView(GamePanel.getInstance());
	}
	
	public void loadLevel() {
		level=new Level(getPointer()).getLevel();
		
	}
}
