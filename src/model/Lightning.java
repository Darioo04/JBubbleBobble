package model;

public class Lightning {
	private static Lightning instance;
	private final int DAMAGE=900;
	
	public static Lightning getInstance() {
		if (instance==null) instance = new Lightning();
		return instance;
	}
	
	private Lightning() {
		
	}
}
