package model;

public class ItemFactory {
	private static ItemFactory instance;
	
	public static ItemFactory getInstance() {
		if (instance == null) instance = new ItemFactory();
		return instance;
	}
	
	public Item createItem(int perc) {
		Item item = null;
		
		return item;
	}
}
