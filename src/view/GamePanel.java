package view;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.GameController;
import controller.LevelCreator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.GameConstants;
import model.GameState;
import model.Player;

public class GamePanel extends StateScreenView {
	private static GamePanel instance;
    private Player player;
    private LevelCreator levelCreator;
    private GameController gameController;
 
	
    public static GamePanel getInstance() {
    	if (instance==null) instance = new GamePanel();
    	return instance;
    }

	private GamePanel() {
		gameController = GameController.getInstance();
		levelCreator = LevelCreator.getInstance();
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
		
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		if (gameController.getGameState() == GameState.GAME) {
			levelCreator.draw(g2);
		}
		player.drawHitbox(g2);
		add(StatusBar.getInstance(),BorderLayout.PAGE_START);
	}
	
	public void setPlayer (Player player) {
		this.player = player;
    }
	
}
