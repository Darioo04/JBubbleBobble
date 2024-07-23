package model;

import java.util.Observable;

@SuppressWarnings("deprecation")

public class Navigator extends Observable  {
	private static Navigator instance;
	
	
	 private Navigator() { 
		 
	 }
	 
	 public static Navigator getInstance() {
		 if (instance == null) instance = new Navigator();
	 	 return instance;
	 }
	 
	 
	 public void navigate(Panels panel) {
		 setChanged();
		 notifyObservers(panel);
	 }
}
