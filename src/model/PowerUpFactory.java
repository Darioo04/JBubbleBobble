package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.GameController;
import controller.LevelCreator;

public class PowerUpFactory {
	
	private static PowerUpFactory instance;
	private Player player = Player.getInstance();
	private int x;
	private int y;
	
	public static PowerUpFactory getInstance() {
		if (instance == null) instance = new PowerUpFactory();
		return instance;
	}
	
	private PowerUpFactory() {
		
	}
	
	public List<PowerUp> createPowerUp() {
		List<PowerUp> powerUps = new ArrayList<>();
		
		if (player.getBubbleBulletsPopped()>=35) { 
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.PINK_CANDY, x, y)); 
			player.increasePinkCandiesCollected(); 
		}
		if (player.getBubbleBulletsPopped()>=35) { 
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.BLUE_CANDY, x, y)); 
			player.increaseBlueCandiesCollected(); 
		}
		if (player.getNumJumps()>=35) { 
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.YELLOW_CANDY, x, y));
			player.increaseYellowCandiesCollected();
		}
		if (player.shoesCanSpawn()) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.SHOES, x, y));
		}
		if (player.getLightningBubblesPopped()>=12) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.CLOCK, x, y));
		}
		if (player.getFireBubblesPopped()>=13) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.DYNAMITE, x, y));
		}
		if (player.getFoodCollected()>=35) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.CHACK_HEART, x, y));
		}
		if (player.getBlueCandiesCollected()>=3) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.CRYSTAL_RING, x, y));
			player.resetBlueCandiesCollected();
		}
		if (player.getYellowCandiesCollected()>=3) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.AMETHYST_RING, x, y));
			player.resetYellowCandiesCollected();
		}
		if (player.getPinkCandiesCollected()>=3) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.RUBY_RING, x, y));
			player.resetPinkCandiesCollected();
		}
		if (player.getFoodCollected()>=12) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.CROSS_OF_THUNDER, x, y));
		}
		if (GameModel.getInstance().getGamesPlayed()%5==0) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.BLUE_LAMP, x, y));
			//aggiungere un controllo per far si che lo spawni uno solo per livello;
		}
	
		return powerUps;
	}
	
	public void getSpawnPoint() {
		char[][] level = LevelCreator.getInstance().getLevel();
		List<Integer> xPoints = new ArrayList<>();
		List<Integer> yPoints = new ArrayList<>();
		for (int i=0; i<level.length; i++) {
			for (int j=0; j<level[0].length; j++) {
				if (level[i][j]==' ') {
					xPoints.add(i);
					yPoints.add(j);
				}
			}
		}
		int point = new Random().nextInt(xPoints.size());
		x = xPoints.get(point);
		y = yPoints.get(point);
	}
}
