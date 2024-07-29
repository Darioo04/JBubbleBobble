package model;

public class LevelEditor extends StateScreen {
	private static LevelEditor instance;
	
	public static LevelEditor getInstance() {
		if (instance==null) instance = new LevelEditor();
		return instance;
	}
	
	private LevelEditor() {
		super(GameState.LEVEL_EDITOR);
	}
}
