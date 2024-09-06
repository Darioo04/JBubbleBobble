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
	private boolean blueLampCanSpawn = true;
	
	public static PowerUpFactory getInstance() {
		if (instance == null) instance = new PowerUpFactory();
		return instance;
	}
	
	private PowerUpFactory() {
		
	}
	
	public List<PowerUp> createPowerUp() {
		List<PowerUp> powerUps = new ArrayList<>();
		
		if (player.getBubblesPopped()>=35) { 
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.PINK_CANDY, x, y)); 
			player.increasePinkCandiesCollected(); 
			player.resetBubblesPopped();
		}
		if (player.getBubbleBulletsPopped()>=35) { 
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.BLUE_CANDY, x, y)); 
			player.increaseBlueCandiesCollected(); 
			player.resetNumBubbleBulletsPopped();
		}
		if (player.getNumJumps()>=35) { 
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.YELLOW_CANDY, x, y));
			player.increaseYellowCandiesCollected();
			player.resetNumJumps();
		}
		if (player.shoesCanSpawn()) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.SHOES, x, y));
			player.resetShoesDistance();
		}
		if (player.getThunderBubblesPopped()>=12) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.CLOCK, x, y));
			player.resetThunderBubblesPopped();
		}
		if (player.getFireBubblesPopped()>=13) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.DYNAMITE, x, y));
			player.resetFireBubblesPopped();
		}
		if (player.getFoodCollected()>=35) {
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.CHACK_HEART, x, y));
			player.resetFoodCollected();
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
			player.resetFoodCollected();
		}
		if (blueLampCanSpawn && GameController.getInstance().getLevel()%5==0) {
			blueLampCanSpawn = false;
			getSpawnPoint();
			powerUps.add(new PowerUp(PowerUpType.BLUE_LAMP, x, y));
			//aggiungere un controllo per far si che lo spawni uno solo per livello;
		}
		if (GameController.getInstance().getLevel()%5!=0) {
			blueLampCanSpawn = true;
		}
	
		return powerUps;
	}
	
	public void getSpawnPoint() {
		char[][] level = LevelCreator.getInstance().getLevel();
		List<Integer> xPoints = new ArrayList<>();
		List<Integer> yPoints = new ArrayList<>();
		for (int i=0; i<level.length; i++) {
			for (int j=0; j<level[0].length; j++) {
				if (level[i][j]!=' ') {
					xPoints.add(j);
					yPoints.add(i);
				}
			}
		}
		int point = new Random().nextInt(xPoints.size());
		x = xPoints.get(point);
		y = yPoints.get(point);
		System.out.println(x + " " + y);
	}
}
