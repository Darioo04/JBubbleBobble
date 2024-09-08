package controller;

import model.Enemy;
import model.EnemyFactory;
import view.EnemyView;

public class SpawnController {
	
	private static SpawnController instance;
	
	public static SpawnController getInstance() {
		if (instance==null) instance = new SpawnController();
		return instance;
	}
	
	private SpawnController() {
		
	}
	
//    public void spawnEnemies() {
//    	EnemyFactory enemyFactory = EnemyFactory.getInstance();
//    	char [][] levelFile = LevelCreator.getInstance().getLevel();
//    	for (int i = 0; i < levelFile.length; i++) {
//    		for (int j = 0; j < levelFile[i].length; j++) {
//    			Enemy enemy = enemyFactory.createEnemy(levelFile[i][j], i, j);
//                if (enemy!=null) {
//                	EnemyView enemyView = new EnemyView(enemy, enemy.getNumIdleSprites(),enemy.getNumRunningSprites(),enemy.getNumJumpingSprites(), enemy.getNumFallingSprites());
//    				enemy.setEnemyView(enemyView);
//                	enemy.addObserver(enemyView);
//    				gamePanel.add(enemyView);
//    				enemies.add(enemy);
//    				enemyViews.add(enemyView);
//                }
//                
//            }
//    	}
//    }
}
