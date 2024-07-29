package model;

import view.WinScreenView;

public class WinScreen extends StateScreen {
	private static WinScreen instance;
	
	public static WinScreen getInstance() {
		if (instance == null) instance = new WinScreen();
		return instance;
	}
	
	private WinScreen() {
		super(GameState.WIN);
		this.loadScreens();
		this.setStateScreenView(WinScreenView.getInstance());
	}
}
