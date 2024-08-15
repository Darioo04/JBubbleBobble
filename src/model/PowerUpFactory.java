package model;

import java.util.List;

public class PowerUpFactory {
	private static PowerUpFactory instance;
	private List<PowerUp> powerUps;
	
	public static PowerUpFactory getInstance() {
		if (instance == null) instance = new PowerUpFactory();
		return instance;
	}
	
//	public List<PowerUp> createPowerUp() {
//		
//	}
}
