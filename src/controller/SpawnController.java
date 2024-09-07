package controller;

public class SpawnController {
	
	private static SpawnController instance;
	
	public static SpawnController getInstance() {
		if (instance==null) instance = new SpawnController();
		return instance;
	}
	
	private SpawnController() {
		
	}
	
	
}
