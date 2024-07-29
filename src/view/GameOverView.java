package view;

public class GameOverView extends StateScreenView {
	private static GameOverView instance;
	
	public static GameOverView getInstance() {
		if (instance == null) instance = new GameOverView();
		return instance;
	}
	
	private GameOverView() {
		
	}
}
