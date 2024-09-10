package controller;

import java.util.List;
import java.util.Random;

import model.Bubble;
import model.BubbleFactory;
import model.Enemy;
import model.EnemyFactory;
import model.Food;
import model.FoodFactory;
import model.GameConstants;
import model.PowerUp;
import model.PowerUpFactory;
import view.EnemyView;

public class SpawnController {
	
	//classe in cui sono presenti tutti i metodi soggetti alla generazione degli elementi di gioco,
	//cioè Nemici, Cibo, Power Ups e Bolle.
	
	private static SpawnController instance;
	
	public static SpawnController getInstance() {
		if (instance==null) instance = new SpawnController();
		return instance;
	}
	
	private SpawnController() {
		
	}
	
    public void spawnEnemies() {
    	EnemyFactory enemyFactory = EnemyFactory.getInstance();
    	char [][] levelFile = LevelCreator.getInstance().getLevel();
    	for (int i = 0; i < levelFile.length; i++) {
    		for (int j = 0; j < levelFile[i].length; j++) {
    			Enemy enemy = enemyFactory.createEnemy(levelFile[i][j], i, j);
                if (enemy!=null) {
                	GameController.getInstance().addEnemy(enemy);
                }
                
            }
    	}
    }
    
    public void spawnFood() {
    	GameController gameController = GameController.getInstance();
		char[][] levelFile = LevelCreator.getInstance().getLevel();
		FoodFactory foodFactory = FoodFactory.getInstance();
		
		int randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
		int randomX = new Random().nextInt(GameConstants.COLS-1)+1;
		
		while (levelFile[randomY][randomX] != ' ') {
			randomX = new Random().nextInt(GameConstants.COLS-1)+1;
			randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
		}
		Food food1 = foodFactory.createItem(Math.random(), randomX*GameConstants.TILE_SIZE, randomY*GameConstants.TILE_SIZE);
		
		randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
		randomX = new Random().nextInt(GameConstants.COLS-1)+1;
		while (levelFile[randomY][randomX] != ' ') {
			randomX = new Random().nextInt(GameConstants.COLS-1)+1;
			randomY = new Random().nextInt(GameConstants.ROWS-1)+1;
		}
		Food food2 = foodFactory.createItem(Math.random(), randomX*GameConstants.TILE_SIZE, randomY*GameConstants.TILE_SIZE);
		
		gameController.addFood(food1);
		gameController.addFood(food2);
	}

	public void spawnPowerUp() {
		PowerUpFactory powerUpFactory = PowerUpFactory.getInstance();
		List<PowerUp> newPowerUps = powerUpFactory.createPowerUp();
		GameController.getInstance().addPowerUps(newPowerUps);
	}

	public void spawnSpecialBubbles() {
		Bubble bubble = BubbleFactory.getInstance().createBubble();
		if (bubble!=null) {
			GameController.getInstance().addBubble(bubble);
			
		}
	}
}
