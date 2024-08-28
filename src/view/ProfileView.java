package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.GameController;
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
	private BufferedImage bubPng;
	private ImageIcon bubPngIcon;
	private JLabel bubLabel;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;

	
	public static ProfileView getInstance() {
		if (instance==null) instance = new ProfileView();
		return instance;
	}
	
	private ProfileView() {
		topScores = new long[3];
		setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        try {
			bubPng = ImageIO.read(getClass().getResource("/sprites/profile-bub.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bubLabel = new JLabel();
		bubLabel.setBounds(7*GameConstants.TILE_SIZE, 3*GameConstants.TILE_SIZE, 7*GameConstants.TILE_SIZE, 7*GameConstants.TILE_SIZE);
		bubPngIcon = new ImageIcon(bubPng.getScaledInstance(bubLabel.getWidth(), bubLabel.getHeight(),Image.SCALE_SMOOTH));
		bubLabel.setIcon(bubPngIcon);
		add(bubLabel);

		font = font.deriveFont(36f);
        nameLabel = new JLabel("hello " + playerName + "!");
        nameLabel.setBounds(5*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, 7*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        nameLabel.setFont(font);
        add(nameLabel);
        
        font = font.deriveFont(20f);
        scoreLabels = new JLabel[topScores.length];
        for (int i = 0; i < topScores.length; i++) {
            scoreLabels[i] = new JLabel((i+1) + " top score! " + topScores[i]);
            scoreLabels[i].setBounds(GameConstants.TILE_SIZE, i*GameConstants.TILE_SIZE + 3*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
            scoreLabels[i].setFont(font);
            add(scoreLabels[i]);
        }
        
        JLabel gamesPlayedLabel = new JLabel("games played!  " + gamesPlayed);
        gamesPlayedLabel.setBounds(GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE, 4*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE);
        gamesPlayedLabel.setFont(font);
        JLabel gamesWonLabel = new JLabel("games won!  " + gamesWon);
        gamesWonLabel.setBounds(GameConstants.TILE_SIZE, 6*GameConstants.TILE_SIZE, 4*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE);
        gamesWonLabel.setFont(font);
        JLabel gamesLostLabel = new JLabel("games lost!  " + gamesLost);
        gamesLostLabel.setBounds(GameConstants.TILE_SIZE, 7*GameConstants.TILE_SIZE, 4*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE);
        gamesLostLabel.setFont(font);
        
        add(gamesPlayedLabel);
        add(gamesWonLabel);
        add(gamesLostLabel);
	}
	
	public void update() {
		playerName = GameController.getInstance().getPlayerName();
		topScores = GameController.getInstance().getTopScores();
		nameLabel.setText("hello " + playerName + "!");
		for (int i = 0; i < topScores.length; i++) {
            scoreLabels[i].setText((i+1)+ " top score!  " + topScores[i]);
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
		g2.setColor(Color.decode("#7700c8"));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void update(Observable o,Object arg) {
		if (o instanceof GameModel) {
			GameModel gm = (GameModel) o;
			this.gamesPlayed=gm.getGamesPlayed();
			this.gamesWon=gm.getGamesWon();
			this.gamesLost=gm.getGamesLost();
		}
		revalidate();
		repaint();
	}
	
}	
