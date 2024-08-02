package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
		statsPanel.setBackground(Color.BLACK);
		
		JPanel labelPlayed = new JPanel(new BorderLayout());
		labelPlayed.setBackground(Color.BLACK);
		labelPlayed.add(new FontView("Games Played: "+gamesPlayed),BorderLayout.CENTER);
		
		JPanel labelWon = new JPanel(new BorderLayout());
		labelWon.setBackground(Color.BLACK);
		labelWon.add(new FontView("Games Won: "+gamesWon),BorderLayout.CENTER);
		
		JPanel labelLost= new JPanel(new BorderLayout());
		labelLost.setBackground(Color.BLACK);
		labelLost.add(new FontView("Games Lost: "+gamesLost),BorderLayout.CENTER);
		
		statsPanel.add(labelPlayed);
		statsPanel.add(labelWon);
		statsPanel.add(labelLost);
		statsPanel.validate();
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
		this.gamesPlayed=gameModel.getGamesPlayed();
		this.gamesWon=gameModel.getGamesWon();
		this.gamesLost=gameModel.getGamesLost();
	}
	
}	
