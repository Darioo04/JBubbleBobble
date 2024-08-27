package model;

public class FoodFactory {
	private static FoodFactory instance;
	
	public static FoodFactory getInstance() {
		if (instance == null) instance = new FoodFactory();
		return instance;
	}
	
	private FoodFactory() {
		
	}
	
	public Food createItem(double num, int x, int y) {
		if (num <= 0.01) {
			return new Food(FoodType.GOLD_CROWN, x, y);
		}
		else if (num <= 0.03) {
			return new Food(FoodType.CURRY_RICE, x, y);
		}
		else if (num <= 0.06) {
			return new Food(FoodType.COCKTAIL, x, y);
		}
		else if (num <= 0.11) {
            return new Food(FoodType.HAMBURGER, x, y);
        }
		else if (num <= 0.19) {
            return new Food(FoodType.ICE_CREAM, x, y);
        }
		else if (num <= 0.28) {
            return new Food(FoodType.WATERMELON, x, y);
        }
		else if (num <= 0.38) {
			return new Food(FoodType.BANANA, x, y);
		}
		else if (num <= 0.5) {
            return new Food(FoodType.CORN, x, y);
        }
		else if (num <= 0.7) {
            return new Food(FoodType.RED_TURNIP, x, y);
        }
		else if (num <= 0.85) {
            return new Food(FoodType.EGGPLANT, x, y);
        }
		else {
            return new Food(FoodType.PEPPER, x, y);
        }
	}
}
