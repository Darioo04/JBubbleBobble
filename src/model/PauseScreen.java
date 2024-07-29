package model;

public class PauseScreen extends StateScreen {
	private static PauseScreen instance;
	private GameState state;
	
	public static PauseScreen getInstance() {
		if (instance==null) instance = new PauseScreen();
		return instance;
	}
	
	private PauseScreen() {
		this.state=GameState.PAUSE;
		this.setFileName(state.getPath());
		this.setNumOptions(state.getNumScreens());
		this.loadScreens();
	}
}
