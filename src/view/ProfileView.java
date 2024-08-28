package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	public static ProfileView instance;
	private String playerName;
	private long topScores[];
	private JLabel nameLabel;
    private JLabel[] scoreLabels;
	private Font font = FontCreator.getInstance().getFont();
//	private GameModel gameModel = GameModel.getInstance();
//	private int gamesPlayed;
//	private int gamesWon;
//	private int gamesLost;
//
//	
	public static ProfileView getInstance() {
		if (instance==null) instance = new ProfileView();
		return instance;
	}
	
	private ProfileView() {
		topScores = new long[3];
		setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

        // Create and configure the name label
        nameLabel = new JLabel("Player: " + playerName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBounds(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        add(nameLabel);
        
        

        // Create and add score labels to the panel
        scoreLabels = new JLabel[topScores.length];
        for (int i = 0; i < topScores.length; i++) {
            scoreLabels[i] = new JLabel("Top Score " + (i + 1) + ": " + topScores[i]);
            scoreLabels[i].setBounds(GameConstants.TILE_SIZE, i*GameConstants.TILE_SIZE + 3*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
            scoreLabels[i].setFont(new Font("Arial", Font.PLAIN, 18));
            add(scoreLabels[i]);
        }
	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public void setTopScores(long[] topScores) {
		this.topScores = topScores;
	}
//	
//	private ProfileView() {
//		setVisible(true);
//		setBackground(Color.BLACK);
//		setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
//		
//		JPanel statsPanel= new JPanel(new GridLayout(3,1,5,5));
//		statsPanel.setBackground(Color.BLACK);
//		
//		UIManager.put("Label.font", font);
//		UIManager.put("Label.Border", BorderFactory.createCompoundBorder( 
//				BorderFactory.createLineBorder(Color.WHITE),
//		        BorderFactory.createEmptyBorder(10, 15, 10, 15)));
//		
//		JLabel labelPlayed = new JLabel("games played: "+gamesPlayed);
//		
//		JLabel labelWon = new JLabel("game won: "+ gamesWon);
//		
//		JLabel labelLost= new JLabel("games lost: "+ gamesLost);
//		
//		statsPanel.add(labelPlayed);
//		statsPanel.add(labelWon);
//		statsPanel.add(labelLost);
//		statsPanel.validate();
//		add(statsPanel);
//		
//		
//
//	}
//	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
//	
//	@Override
//	public void update(Observable o,Object arg) {
//		if (o instanceof GameModel) {
//			this.gamesPlayed=gameModel.getGamesPlayed();
//			this.gamesWon=gameModel.getGamesWon();
//			this.gamesLost=gameModel.getGamesLost();
//			repaint();
//		}
//		
//	}
	
}	
