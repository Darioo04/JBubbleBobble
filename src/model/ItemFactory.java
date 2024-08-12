package model;

public class ItemFactory {
	private static ItemFactory instance;
	
	public static ItemFactory getInstance() {
		if (instance == null) instance = new ItemFactory();
		return instance;
	}
	
	private ItemFactory() {
		
	}
	
	public Item createItem(int random) {
		Item item = null;
		
		return item;
	}
}
