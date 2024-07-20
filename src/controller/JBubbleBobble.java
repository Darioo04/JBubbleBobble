package controller;



import model.GameModel;
import view.FullScreenFrame;
import view.GamePanel;

public class JBubbleBobble {
	public static void main(String[] args) {
		
		GameModel model = new GameModel();
		GameController controller = new GameController(model);
		FullScreenFrame fullScreenFrame = FullScreenFrame.getInstance();
		GamePanel gamePanel = new GamePanel(model);
		gamePanel.addKeyListener(controller.getPlayerController());
		fullScreenFrame.add(gamePanel);
		fullScreenFrame.setFocusable(true);
		
	}

}
