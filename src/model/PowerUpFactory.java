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
		
		if (player.getBubblesPopped()>=35 && checkPowerUps(PowerUpType.PINK_CANDY)) { 
			powerUps.add( getPowerUp(PowerUpType.PINK_CANDY) );
			player.increasePinkCandiesCollected(); 
			player.resetBubblesPopped();
		}
		if (player.getBubbleBulletsPopped()>=35 && checkPowerUps(PowerUpType.BLUE_CANDY)) { 
			powerUps.add( getPowerUp(PowerUpType.BLUE_CANDY) );
			player.increaseBlueCandiesCollected(); 
			player.resetNumBubbleBulletsPopped();
		}
		if (player.getNumJumps()>=35 && checkPowerUps(PowerUpType.YELLOW_CANDY)) { 
			powerUps.add( getPowerUp(PowerUpType.YELLOW_CANDY) );
			player.increaseYellowCandiesCollected();
			player.resetNumJumps();
		}
		if (player.shoesCanSpawn() && checkPowerUps(PowerUpType.SHOES)) {
			powerUps.add( getPowerUp(PowerUpType.SHOES) );
			player.resetShoesDistance();
		}
		if (player.getThunderBubblesPopped()>=12 && checkPowerUps(PowerUpType.CLOCK)) {
			powerUps.add( getPowerUp(PowerUpType.CLOCK) );
			player.resetThunderBubblesPopped();
		}
		if (player.getFireBubblesPopped()>=13 && checkPowerUps(PowerUpType.DYNAMITE)) {
			powerUps.add( getPowerUp(PowerUpType.DYNAMITE) );
			player.resetFireBubblesPopped();
		}
		if (player.getFoodCollected()>=35 && checkPowerUps(PowerUpType.CHACK_HEART)) {
			powerUps.add( getPowerUp(PowerUpType.CHACK_HEART) );
			player.resetFoodCollected();
		}
		if (player.getBlueCandiesCollected()>=3 && checkPowerUps(PowerUpType.CRYSTAL_RING)) {
			powerUps.add( getPowerUp(PowerUpType.CRYSTAL_RING) );
			player.resetBlueCandiesCollected();
		}
		if (player.getYellowCandiesCollected()>=3 && checkPowerUps(PowerUpType.AMETHYST_RING)) {
			powerUps.add( getPowerUp(PowerUpType.AMETHYST_RING) );
			player.resetYellowCandiesCollected();
		}
		if (player.getPinkCandiesCollected()>=3 && checkPowerUps(PowerUpType.RUBY_RING)) {
			powerUps.add( getPowerUp(PowerUpType.RUBY_RING) );
			player.resetPinkCandiesCollected();
		}
		if (player.getFoodCollected()>=12 && checkPowerUps(PowerUpType.CROSS_OF_THUNDER)) {
			powerUps.add( getPowerUp(PowerUpType.CROSS_OF_THUNDER) );
			player.resetFoodCollected();
		}
		if (blueLampCanSpawn && GameController.getInstance().getLevel()%5==0 && checkPowerUps(PowerUpType.BLUE_LAMP)) {
			powerUps.add( getPowerUp(PowerUpType.BLUE_LAMP) );
			blueLampCanSpawn = false;
		}
		if (GameController.getInstance().getLevel()%5!=0) {
			blueLampCanSpawn = true;
		}
	
		return powerUps;
	}
	
	private PowerUp getPowerUp(PowerUpType type) {
		getSpawnPoint();
		x = x * GameConstants.TILE_SIZE + GameConstants.SCALE;
		y = y * GameConstants.TILE_SIZE + GameConstants.SCALE;
		return new PowerUp(type, x, y);
	}
	
	public void getSpawnPoint() {
		char[][] level = LevelCreator.getInstance().getLevel();
		List<Integer> xPoints = new ArrayList<>();
		List<Integer> yPoints = new ArrayList<>();
		for (int i=0; i<level.length; i++) {
			for (int j=0; j<level[0].length; j++) {
				if (level[i][j] == ' ') {
					xPoints.add(j);
					yPoints.add(i);
				}
			}
		}
		int point = new Random().nextInt(xPoints.size());
		x = xPoints.get(point);
		y = yPoints.get(point);
//		System.out.println(x + " " + y);
	}
	
	private boolean checkPowerUps(PowerUpType type) {
		List<PowerUp> powerUps = GameController.getInstance().getPowerups();
		return powerUps.stream().map(powerUp -> powerUp.getType()).filter(powerUpType -> powerUpType == type).count()==0;
	}
}
