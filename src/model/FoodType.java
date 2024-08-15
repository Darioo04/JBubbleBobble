package model;

public enum FoodType {
	
	PEPPER(10),
	EGGPLANT(20),
	RED_TURNIP(50),
	CORN(150),
	BANANA(500),
	WATERMELON(600),
	ICE_CREAM(1000),
	HAMBURGER(2000),
	COCKTAIL(3000),
	CURRY_RICE(8000),
	GOLD_CROWN(10000);
	
	
	private int point;
	
	FoodType(int point) {
		this.point=point;
	}
	
	public int getPoint() {
		return point;
	}
}
