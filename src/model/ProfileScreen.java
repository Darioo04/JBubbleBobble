package model;

public class ProfileScreen extends StateScreen {
	private static ProfileScreen instance;
	private GameState state;
	
	public static ProfileScreen getInstance() {
		if (instance==null) instance = new ProfileScreen();
		return instance;
	}
	
	private ProfileScreen() {
		super(GameState.SELECT_PROFILE);
		this.loadScreens();
	}
}
