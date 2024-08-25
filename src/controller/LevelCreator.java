package controller;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import model.GameConstants;
import model.Wall;


public class LevelCreator {
	
	private static LevelCreator instance;
	private final String path = "/Levels/level-";
	private String wallPath;
	private char[][] file; // [rows][cols]
	private Rectangle[][] tilesHitboxes;
//	private GameController gameController;
	
	public static LevelCreator getInstance() {
		if (instance == null) instance = new LevelCreator();
		return instance;
	}
	
	private LevelCreator() { 
		
	}
	
	public void setWall() {
		Wall wall = new Wall(GameController.getInstance().getLevel());
		wallPath = wall.getPath();
	}
	
	private void loadTilesHitboxes() {
		this.tilesHitboxes = new Rectangle[GameConstants.ROWS][GameConstants.COLS];
		int y = 0;
		for (int i=0; i<file.length; i++) {
			int x = 0;
			for (int j=0; j<file[0].length; j++) {
				char tile=file[i][j];
				if (tile == '1') {
					tilesHitboxes[i][j] = new Rectangle(x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
				}else {
					tilesHitboxes[i][j] = new Rectangle(0, 0, 1, 1);
				}
				x+=GameConstants.TILE_SIZE;
			}
			y+=GameConstants.TILE_SIZE;
		}
	}
	
	public void loadLevel() {
		this.file = new char[GameConstants.ROWS][GameConstants.COLS];
		InputStream inFile;
		try {
			if (GameController.getInstance().getLevel() == 25) {
				String projectPath = System.getProperty("user.dir");
		        String path = projectPath + "/custom-level/custom-level.txt";
		        inFile = new FileInputStream(path);
			}
			else {
				inFile = getClass().getResourceAsStream(path + GameController.getInstance().getLevel() + ".txt");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
			int k=0;
			while (br.ready()) {
				String line = br.readLine();
				for (int i=0; i<line.length(); i++) {
					file[k][i] = line.charAt(i);
				}
				k++;
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadTilesHitboxes();
	}
	
	public char[][] getLevel() {
		return file;
	}
	
	public String getWall() {
		return wallPath;
	}
	
	public Rectangle[][] getTilesHitboxes() {
        return tilesHitboxes;
    }

}
