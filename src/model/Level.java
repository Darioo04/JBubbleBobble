package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import model.GameConstants;

@SuppressWarnings("deprecation")

public class Level extends Observable {
	private int level;
	private final String path = "res/Levels/level-";
	private Tiles[][] tiles;
	private char[][] file;
	
	
	public Level(int level) { 
		this.tiles = new Tiles[GameConstants.ROWS][GameConstants.COLS];
		this.file = new char[GameConstants.ROWS][GameConstants.COLS];
		this.level=level;
		readLevel();
		initLevel();
	}
	
	public char[][] readLevel() {
		try {
			File inFile= new File(path + level + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(inFile));
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
		return file;
	}
	
	public void initLevel() {
		for (int i=0; i<file.length; i++) {
			for (int j=0; j<file[0].length; j++) {
				tiles[i][j] = 
					switch (file[i][j]) {
						case '1' -> new Wall(level);
						case ' ' ->  new EmptyBlock();
//						case 'R' -> new Robot();
//						case 'F' -> new Ghost();
						default -> throw new IllegalArgumentException("Unexpected value: " + file[i][j]); 
				};
			}
		}
		
//		for (Tiles[] row : tiles) {
//			for (Tiles tile : row) {
//				tile.addObserver(this);
//			}
//		}
	}
	
	public Tiles[][] getLevel() {
		return tiles;
	}

}
