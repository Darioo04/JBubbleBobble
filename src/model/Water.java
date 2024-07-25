package model;

public class Water {
	private static Water instance;
	
	public static Water getInstance() {
		if (instance==null) instance = new Water();
		return instance;
	}
	
	private Water() {
		
	}
}
