package controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import model.EmptyBlock;
import model.GameConstants;
import model.Tile;
import model.Wall;


public class LevelCreator {
	
	private static LevelCreator instance;
	private final String path = "/Levels/level-";
	private EmptyBlock emptyBlock;
	private Wall wall;
//	private Tile[] tiles;
	private char[][] file;
	
	public static LevelCreator getInstance() {
		if (instance == null) instance = new LevelCreator();
		return instance;
	}
	
	private LevelCreator() { 
//		this.tiles = new Tile[GameConstants.ROWS][GameConstants.COLS];
//		this.file = new char[GameConstants.ROWS][GameConstants.COLS];
//		readLevel();
//		initLevel();
	}
	
	public void loadSprites() {
		emptyBlock = new EmptyBlock();
		wall = new Wall(GameController.level);
	}
	
	public void loadLevel() {
		loadSprites();
		this.file = new char[GameConstants.ROWS][GameConstants.COLS];
		try {
			InputStream inFile= getClass().getResourceAsStream(path + GameController.level + ".txt");
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
//		return file;
	}
	
//	public void draw(Graphics2D g2) {
//		int col = 0;
//		int row = 0;
//		int x = 0;
//		int y = 0;
//		while(row < GameConstants.ROWS && col < GameConstants.COLS) {
//			char tile = file[row][col];
//			switch (tile) {
//				case '1' -> g2.drawImage(wall.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
//				
//				case ' ' -> g2.drawImage(emptyBlock.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
//				
//				case 'R' -> g2.drawImage(emptyBlock.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
//				
//				default ->
//				throw new IllegalArgumentException("Unexpected value: " + tile);
//				}
//			col++;
//			x += GameConstants.TILE_SIZE;
//			
//			if (col == GameConstants.COLS ) {
//				col = 0;
//				row++;
//				x = 0;
//				y += GameConstants.TILE_SIZE;
//			}
//		}
//	}
	
	public void draw(Graphics2D g2d) {
		
		int y = 0;
		for (int i=0; i<file.length; i++) {
			int x = 0;
			for (int j=0; j<file[0].length; j++) {
				char tile=file[i][j];
				switch (tile) {
					case '1' -> g2d.drawImage(wall.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
				
					case ' ' -> g2d.drawImage(emptyBlock.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
				
					case 'R' -> g2d.drawImage(emptyBlock.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
				
					default -> throw new IllegalArgumentException("Unexpected value: " + tile);
				}
				x+=GameConstants.TILE_SIZE;
			}
			y+=GameConstants.TILE_SIZE;
		}
		
	}
	
	public char[][] getLevel() {
		return file;
	}

}
