package model;

import view.MenuScreenView;

public class MenuScreen extends StateScreen {
	
	private static MenuScreen instance;
	
	public static MenuScreen getInstance(){
        if(instance == null) instance = new MenuScreen();
        return instance;
    }
	
	private MenuScreen(){
		this.setGameState(GameState.MENU);
        this.setFileName(getGameState().getPath());
        this.setNumOptions(getGameState().getNumScreens());
        this.loadScreens();
        this.setStateScreenView(MenuScreenView.getInstance());
    }
}
