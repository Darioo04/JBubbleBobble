package controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import model.GameConstants;
import model.Invader;
import model.Wall;
import view.EnemyView;


public class LevelCreator {
	
	private static LevelCreator instance;
	private final String path = "/Levels/level-";
	private Wall wall;
	private char[][] file; // [rows][cols]
	
	public static LevelCreator getInstance() {
		if (instance == null) instance = new LevelCreator();
		return instance;
	}
	
	private LevelCreator() { 

	}
	
	public void loadSprites() {
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
	}
	
	public void draw(Graphics2D g2d) {
		
		int y = 0;
		for (int i=0; i<file.length; i++) {
			int x = 0;
			for (int j=0; j<file[0].length; j++) {
				char tile=file[i][j];
				switch (tile) {
					case '1' -> g2d.drawImage(wall.getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
						
					case 'R' -> g2d.drawImage(new EnemyView(new Invader()).getSprite(), x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
					
					default -> {}
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
