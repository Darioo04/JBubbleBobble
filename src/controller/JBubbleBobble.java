package controller;

import view.FullScreenFrame;
import view.GamePanel;

public class JBubbleBobble {
	public static void main(String[] args) {
		FullScreenFrame fullScreenFrame = new FullScreenFrame();
		GamePanel gamePanel = new GamePanel();
		fullScreenFrame.add(gamePanel);
	}

}
