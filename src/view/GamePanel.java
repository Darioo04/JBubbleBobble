package view;


import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.GameConstants;
import model.Player;


public class GamePanel extends StateScreenView {
	private static GamePanel instance;
    private Player player;
 
	
    public static GamePanel getInstance() {
    	if (instance==null) instance = new GamePanel();
    	return instance;
    }

	private GamePanel() {
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public void setPlayer (Player player) {
		this.player = player;
    }
	
}
