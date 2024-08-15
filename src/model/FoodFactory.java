package model;

public class FoodFactory {
	private static FoodFactory instance;
	
	public static FoodFactory getInstance() {
		if (instance == null) instance = new FoodFactory();
		return instance;
	}
	
	private FoodFactory() {
		
	}
	
	public Food createItem(int random) {
		Food item = null;
		
		return item;
	}
}
