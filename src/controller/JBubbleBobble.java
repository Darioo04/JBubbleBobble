package controller;


import model.Player;
import view.MainFrame;
import view.GamePanel;

public class JBubbleBobble {
	public static void main(String[] args) {
		GameController controller = GameController.getInstance();
		MainFrame mainFrame = MainFrame.getInstance();
		GamePanel gamePanel = GamePanel.getInstance();
		Player player = controller.getPlayer();
		player.setPath("/sprites/Bub-0.png");
		gamePanel.addKeyListener(controller.getPlayerController());
		gamePanel.setPlayer(player);
		mainFrame.add(gamePanel);
		mainFrame.setFocusable(true);
//		mainFrame.setContentPane(gamePanel);
		
	}

}
