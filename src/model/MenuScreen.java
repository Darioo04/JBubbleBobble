package model;

import view.MenuScreenPanel;

public class MenuScreen extends StateScreen {
	
	private static MenuScreen instance;
	private GameState state;
	
	public static MenuScreen getInstance(){
        if(instance == null) instance = new MenuScreen();
        return instance;
    }
	
	private MenuScreen(){
		this.state=GameState.MENU;
        this.setFileName(state.getPath());
        this.setNumOptions(state.getNumScreens());
        this.loadScreens();
        this.setStateScreenPanel(MenuScreenPanel.getInstance());
    }
}
