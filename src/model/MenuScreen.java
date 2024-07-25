package model;

import view.MenuScreenPanel;

public class MenuScreen extends StateScreen {
	
	private static MenuScreen instance;
	
	public static MenuScreen getInstance(){
        if(instance == null) instance = new MenuScreen();
        return instance;
    }
	
	private MenuScreen(){
        this.setFileName("menu-");
        this.setNumOptions(3);
        this.loadScreens();
        this.setStateScreenView(MenuScreenPanel.getInstance());
    }
}
