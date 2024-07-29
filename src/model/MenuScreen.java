package model;

import view.MenuScreenView;

public class MenuScreen extends StateScreen {
	
	private static MenuScreen instance;
	
	public static MenuScreen getInstance(){
        if(instance == null) instance = new MenuScreen();
        return instance;
    }
	
	private MenuScreen(){
		super(GameState.MENU);
        this.loadScreens();
        this.setStateScreenView(MenuScreenView.getInstance());
    }
}
