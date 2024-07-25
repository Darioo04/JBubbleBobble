package model;

public class SelectProfileScreen extends StateScreen {
	private static SelectProfileScreen instance;
	
	public static SelectProfileScreen getInstance() {
		if (instance==null) instance = new SelectProfileScreen();
		return instance;
	}
	
	private SelectProfileScreen() {
		
	}
}
