package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.GameController;
import controller.LevelCreator;

public class BubbleFactory {
	/*
	 * classe che si occupa di generare le bolle speciali
	 * implementa il factory pattern
	 */
	private static BubbleFactory instance;
	private int spawnCounter = 540;
	private final int SPAWN_DELAY = 540;
	private char[][] level;
	private int spawnX, spawnY;
	
	public static BubbleFactory getInstance() {
		if (instance==null) instance = new BubbleFactory();
		return instance;
	}
	
	private BubbleFactory() {
		
	}
	
	public Bubble createBubble() { 
		if (spawnCounter++>=SPAWN_DELAY) { //la bolla viene creata ogni 9 secondi
			spawnCounter=0;
			return getBubble();
		}
		return null;
	}
	/*
	 * se il player è nel livello finale, allora verranno generate solo ThunderBubble
	 */
	public Bubble getBubble() {
		if (GameController.getInstance().getLevel()==24) {
			getFinalLevelSpawnBubbles();
			spawnX = spawnX * GameConstants.TILE_SIZE + GameConstants.SCALE;
			spawnY = spawnY * GameConstants.TILE_SIZE + GameConstants.SCALE;
			return new ThunderBubble(spawnX, spawnY);
		}
		else {
			getSpawnBubbles();
			if (spawnX > -1) {
				spawnX = spawnX * GameConstants.TILE_SIZE + GameConstants.SCALE;
				spawnY = spawnY * GameConstants.TILE_SIZE + GameConstants.SCALE;
				int perc = new Random().nextInt(101);
				if (perc<=1) return new SupremeBubble(spawnX,spawnY);
				else if (perc<=15) return new WaterBubble(spawnX,spawnY);
				else if (perc<=35) return new ExtendBubble(spawnX, spawnY);
				else if (perc<=65) return new FireBubble(spawnX,spawnY);
				else if (perc<=100) return new ThunderBubble(spawnX, spawnY);
			}
		}
		return null;
	}
	/*
	 * spawner per l'ultimo livello
	 */
	private void getFinalLevelSpawnBubbles() {
		List<Integer> xPoints = new ArrayList<>();
		level = LevelCreator.getInstance().getLevel();
		spawnY = level.length-1;
		int range = 2;
		for (int i=1; i<=range; i++) {
			xPoints.add(i);
			xPoints.add(level[0].length-i-1);
		}
		spawnX = (xPoints.size()!=0) ? xPoints.get( new Random().nextInt(0,xPoints.size())) : -1;
	}
	/*
	 * le coordinate x vengono aggiunte alla lista solo se il blocco è vuoto
	 */
	private void getSpawnBubbles() {
		List<Integer> xPoints = new ArrayList<>();
    	level = LevelCreator.getInstance().getLevel();
    	spawnY = level.length-1;
    	int rowLength = level[0].length;
    	for (int i=0; i<rowLength; i++) {
    		if (level[spawnY][i] == ' ') {
    			xPoints.add(i);
    		}
    	}
    	spawnX = (xPoints.size()!=0) ? xPoints.get( new Random().nextInt(0,xPoints.size())) : -1;
    }
}
