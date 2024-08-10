package controller;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.GameConstants;
import model.Invader;
import model.Wall;
import view.EnemyView;


public class LevelCreator {
	
	private static LevelCreator instance;
	private final String path = "/Levels/level-";
	private Wall wall;
	private char[][] file; // [rows][cols]
	private Rectangle[][] tilesHitboxes;
//	private GameController gameController;
	
	public static LevelCreator getInstance() {
		if (instance == null) instance = new LevelCreator();
		return instance;
	}
	
	private LevelCreator() { 
//		gameController = GameController.getInstance();
	}
	
	public void loadSprites() {
		wall = new Wall(GameController.getInstance().getLevel());
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
		loadSprites();
		this.file = new char[GameConstants.ROWS][GameConstants.COLS];
		try {
			InputStream inFile= getClass().getResourceAsStream(path + GameController.getInstance().getLevel() + ".txt");
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
	
	public void draw(Graphics2D g2d) {
		
		int y = 0;
		for (int i=0; i<file.length; i++) {
			int x = 0;
			for (int j=0; j<file[0].length; j++) {
				char tile=file[i][j];
				if (tile == '1') g2d.drawImage(wall.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
				x+=GameConstants.TILE_SIZE;
			}
			y+=GameConstants.TILE_SIZE;
		}
		
	}
	
	public char[][] getLevel() {
		return file;
	}
	
	public Rectangle[][] getTilesHitboxes() {
        return tilesHitboxes;
    }

}
