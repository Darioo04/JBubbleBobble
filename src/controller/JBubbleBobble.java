package controller;




import model.Player;
import view.FullScreenFrame;
import view.GamePanel;

public class JBubbleBobble {
	public static void main(String[] args) {
		GameController controller = new GameController();
		FullScreenFrame fullScreenFrame = FullScreenFrame.getInstance();
		GamePanel gamePanel = GamePanel.getInstance();
		gamePanel.addKeyListener(controller.getPlayerController());
		gamePanel.setPlayer(controller.getPlayer());
		fullScreenFrame.add(gamePanel);
		fullScreenFrame.setFocusable(true);
		
	}

}
