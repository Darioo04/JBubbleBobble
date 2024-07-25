package model;

public class ProfileScreen extends StateScreen {
	private static ProfileScreen instance;
	private GameState state;
	
	public static ProfileScreen getInstance() {
		if (instance==null) instance = new ProfileScreen();
		return instance;
	}
	
	private ProfileScreen() {
		this.state=GameState.SELECT_PROFILE;
		this.setFileName(state.getPath());
		this.setNumOptions(state.getNumScreens());
		this.loadScreens();
	}
}
