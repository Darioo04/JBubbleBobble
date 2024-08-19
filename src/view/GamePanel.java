package view;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.GameController;
import controller.LevelCreator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.GameConstants;
import model.GameState;
import model.Player;
import model.Wall;

public class GamePanel extends StateScreenView {
	private static GamePanel instance;
    private Player player;
    private LevelCreator levelCreator;
    private BufferedImage wall;
    private GameController gameController;
    private StatusBar statusBar;
	
    public static GamePanel getInstance() {
    	if (instance==null) instance = new GamePanel();
    	return instance;
    }

	private GamePanel() {
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT-40));
		gameController = GameController.getInstance();
		levelCreator = LevelCreator.getInstance();
		statusBar  = StatusBar.getInstance();
		add(statusBar,BorderLayout.NORTH);

		
		
	}
	
	public void loadWallSprite() {
		levelCreator.setWall();
		try {
			wall = ImageIO.read(getClass().getResource(levelCreator.getWall()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		if (gameController.getGameState() == GameState.GAME) {
			draw(g2);
		}
//		player.drawHitbox(g2);
	}
	
	public void draw(Graphics2D g2d) {
		loadWallSprite();
		levelCreator.loadLevel();
		char[][] level = levelCreator.getLevel();
		int y = 0;
		for (int i=0; i<level.length; i++) {
			int x = 0;
			for (int j=0; j<level[0].length; j++) {
				char tile=level[i][j];
				if (tile == '1') g2d.drawImage(wall, x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, null);
				x+=GameConstants.TILE_SIZE;
			}
			y+=GameConstants.TILE_SIZE;
		}	
	}
	
	public void setPlayer (Player player) {
		this.player = player;
    }
	
}
