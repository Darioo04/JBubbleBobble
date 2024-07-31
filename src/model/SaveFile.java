package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Predicate;

public class SaveFile implements Serializable {
	private GameModel gameModel;
	
	public SaveFile() {
		gameModel = GameModel.getInstance();
	}
	
	public void save() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("/Saves/save1.txt"));
			bw.write(gameModel.getGamesPlayed());
			bw.write(gameModel.getGamesWon());
			bw.write(gameModel.getGamesLost());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("/Saves/save1.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
