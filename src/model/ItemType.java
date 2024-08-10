package model;

public enum ItemType {
	
	PEPPER(10,"pepper"),
	EGGPLANT(20,"eggplant"),
	RED_TURNIP(50,"turnip"),
	CORN(150,"corn"),
	BANANA(500,"banana"),
	WATERMELON(600,"watermelon"),
	ICE_CREAM(1000,"IceCream"),
	HAMBURGER(2000,"hamburger"),
	COCKTAIL(3000,"Cocktail"),
	CURRY_RICE(8000,"CurryRice"),
	GOLD_CROWN(10000,"GoldCrown");
	
	
	private int point;
	private String name;
	
	ItemType(int point,String path) {
		this.point=point;
		this.name=path;
	}
	
	public int getPoint() {
		return point;
	}
	
	public String getName() {
		return name;
	}
}
