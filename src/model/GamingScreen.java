//package model;
//
//import java.util.Observable;
//
//import view.GamePanel;
//
//@SuppressWarnings("deprecation")
//public class GamingScreen extends Observable{
//	private static GamingScreen instance;
//	private GamePanel gamePanel;
//	private Tiles[][] level;
//	
//	
//	public static GamingScreen getInstance() {
//		if (instance==null) instance = new GamingScreen();
//		return instance;
//	}
//	
//	
//	@SuppressWarnings("deprecation")
//	private GamingScreen() {
////		this.setGameState(GameState.GAME);
////        this.setFileName(getGameState().getPath());
////        this.setNumOptions(getGameState().getNumScreens());
////		this.loadScreens();
//		gamePanel = GamePanel.getInstance();
//		this.addObserver(gamePanel);
//	}
//	
////	public void loadLevel() {
////		level=new Level(getPointer()).getLevel();
////		
////	}
//	
//	public void update() {
//		setChanged();
//        notifyObservers();
//	}
//	
//	public GamePanel getGamePanel() {
//        return gamePanel;
//    }
//}
