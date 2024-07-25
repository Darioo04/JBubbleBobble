package model;

public class Fire {
	private static Fire instance;
	private final int DAMAGE=200;
	
	public static Fire getInstance() {
		if (instance==null) instance = new Fire();
		return instance;
	}
	
	private Fire() {
		
	}
}

