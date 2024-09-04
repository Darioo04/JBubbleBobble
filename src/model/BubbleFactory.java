package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.LevelCreator;

public class BubbleFactory {
	private static BubbleFactory instance;
	private char[][] levelFile;
	private int spawnX, spawnY;
	private Player player;
	List<Integer> spawnPoints;
	public static BubbleFactory getInstance() {
		if (instance==null) instance = new BubbleFactory();
		return instance;
	}
	
	private BubbleFactory() {
		player=Player.getInstance();
	}
	
	public Bubble createBubble() { //chiami il riferimento BubbleFactory::createBubble
		int perc = new Random().nextInt(100);
		spawnBubbles();
		if (perc<=1) return new SupremeBubble(spawnX,spawnY);
		else if (perc<=8) return new WaterBubble(spawnX,spawnY);
		else if (perc<=15) return new FireBubble(spawnX,spawnY);
		else if (perc<=25) return new ThunderBubble(spawnX,spawnY);
		else if (perc<=35) return new ExtendBubble(spawnX, spawnY);
		
		else return null;
	}
	
	private void spawnBubbles() {
		spawnPoints = new ArrayList<>();
    	levelFile = LevelCreator.getInstance().getLevel();
    	spawnY = levelFile.length-1;
    	int rowLength = levelFile[0].length;
    	for (int i=0; i<rowLength; i++) {
    		if (levelFile[spawnY][i] == ' ') {
    			spawnPoints.add(i);
    		}
    	}
    	
    	spawnX = spawnPoints.get( new Random().nextInt(spawnPoints.size()) );
    }

}
