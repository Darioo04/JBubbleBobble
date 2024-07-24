package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")

public class Level extends Observable {
	private int level;
	private final String path = "/res/sprite/levels/level-";
	private Tiles[][] tiles = new Tiles[16][14];
//	private Duration time = Duration.ofSeconds(0);
	
	public Level(int level) { 
		this.level=level;
		String[][] file = readLevel();
		initLevel(file);
	}
	
	public String[][] readLevel() {
		String[][] file = new String[16][14];
		try {
			File inFile= new File(path + level + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			int k=0;
			while (br.ready()) {
				String[] line = br.readLine().split(" ");
				for (int i=0; i<line.length; i++) {
					file[k][i] = line[i];
				}
				k++;
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public void initLevel(String[][] file) {
		for (int i=0; i<file.length; i++) {
			for (int j=0; j<file[0].length; j++) {
				tiles[i][j] = 
					switch (file[i][j]) {
						case "1" -> new Wall(level);
						case "0" ->  new EmptyBlock();
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
	
//	public void update(Observable o, Object arg) {
//		setChanged();
//		notifyObservers();
//	}
}
