package controller;


import model.Player;
import view.MainFrame;
import view.GamePanel;

public class JBubbleBobble {
	public static void main(String[] args) {
		GameController controller = GameController.getInstance();
		controller.startGame();
		
	}

}
