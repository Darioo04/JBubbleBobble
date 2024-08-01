package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.GameConstants;
import model.GameModel;
import model.GameState;

@SuppressWarnings("deprecation")

public class ProfileView extends StateScreenView {
	private static ProfileView instance;
	private GameModel gameModel = GameModel.getInstance();
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;

	
	public static ProfileView getInstance() {
		if (instance==null) instance = new ProfileView();
		return instance;
	}
	
	private ProfileView() {
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
		JPanel statsPanel= new JPanel(new GridLayout(3,1,5,5));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JLabel labelPlayed = new JLabel();
		labelPlayed.add(new FontView("Games Played: "+gamesPlayed),BorderLayout.CENTER);
		
		JLabel labelWon = new JLabel();
		labelWon.add(new FontView("Games Won: "+gamesWon),BorderLayout.CENTER);
		
		JLabel labelLost= new JLabel();
		labelLost.add(new FontView("Games Lost: "+gamesLost),BorderLayout.CENTER);
		
		statsPanel.add(labelPlayed);
		statsPanel.add(labelWon);
		statsPanel.add(labelLost);
		
		add(statsPanel);
		
		UIManager.put("Label.Border", BorderFactory.createCompoundBorder( 
				BorderFactory.createLineBorder(Color.WHITE),
		        BorderFactory.createEmptyBorder(10, 15, 10, 15)));

	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void update(Observable o,Object arg) {
		super.update(o, arg);
		this.gamesPlayed=gameModel.getGamesPlayed();
		this.gamesWon=gameModel.getGamesWon();
		this.gamesLost=gameModel.getGamesLost();
	}
	
	public static void main(String[] args) {
		new ProfileView();
	}
}	
