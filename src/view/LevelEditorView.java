package view;

import java.awt.Color;
import java.awt.Dimension;
import model.GameConstants;

public class LevelEditorView extends StateScreenView {
	
	public static LevelEditorView instance;
	
	public static LevelEditorView getInstance() {
		if (instance == null) instance = new LevelEditorView();
		return instance;
	}

	private LevelEditorView() {
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        loadTiles();
        this.setVisible(true);
	}
	
	private void loadTiles() {
		int y = GameConstants.SCREEN_HEIGHT - GameConstants.TILE_SIZE;
		int x = 0;
		for (int i=0; i<GameConstants.COLS; i++) {
			TileView tile = new TileView(i, x, y);
			this.add(tile);
		}
	}
}
