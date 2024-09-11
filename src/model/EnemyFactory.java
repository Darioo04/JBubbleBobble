package model;

public class EnemyFactory {
	/*
	 * classe che si occupa di scegliere quale nemico generare 
	 * in base al carattere passato come argomento del metodo
	 * implementa il singleton pattern
	 */
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
			
				case 'B' -> new Banebou(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'H' -> new Hidegons(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'P' -> new PulPul(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
			
				case 'S' -> new SuperDrunk(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
				
				case 'Z' -> new ZenChan(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);
				
				default -> null;
		};
	}
}
