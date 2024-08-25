package model;

public class EnemyFactory {
	private static EnemyFactory instance;
	
	public static EnemyFactory getInstance() {
		if (instance == null) instance = new EnemyFactory();
		return instance;
	}
	
	private EnemyFactory() {
		
	}
	
	public Enemy createEnemy(char tile,int i,int j) {
		return 
			switch (tile) {
				case 'R' -> new Invader(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'B' -> new Monsta(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'H' -> new Hidegons(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'P' -> new PulPul(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'S' -> new SuperDrunk(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
				
				case 'Z' -> new ZenChan(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
				
				default -> null;
		};
	}
}
