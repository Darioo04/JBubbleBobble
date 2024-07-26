package model;

public class LevelEditor extends StateScreen {
	private static LevelEditor instance;
	
	public static LevelEditor getInstance() {
		if (instance==null) instance = new LevelEditor();
		return instance;
	}
	
	private LevelEditor() {
		this.setGameState(GameState.LEVEL_EDITOR);
		this.setNumOptions(getGameState().getNumScreens());
		this.setFileName(getGameState().getPath());
	}
}
