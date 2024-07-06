package view;

import model.GameConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;


public class GamePanel extends JPanel {
	
	public GamePanel() {
        Dimension screenSize = getSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        double scaleX = (double) screenWidth / GameConstants.ORIGINAL_WIDTH;
        double scaleY = (double) screenHeight / GameConstants.ORIGINAL_HEIGHT;
        GameConstants.SCALE = Math.min(scaleX, scaleY);

        int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
        
        this.setSize(new Dimension(scaledWidth, scaledHeight));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // get JFrame dimensions
        Dimension screenSize = getSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // get scale value
        double scaleX = (double) screenWidth / GameConstants.ORIGINAL_WIDTH;
        double scaleY = (double) screenHeight / GameConstants.ORIGINAL_HEIGHT;
        GameConstants.SCALE = Math.min(scaleX, scaleY);

        // scaled dimensions
        int scaledWidth = (int) (GameConstants.ORIGINAL_WIDTH * GameConstants.SCALE);
        int scaledHeight = (int) (GameConstants.ORIGINAL_HEIGHT * GameConstants.SCALE);
        
        // set center
        int x = (screenWidth - scaledWidth) / 2;
        int y = (screenHeight - scaledHeight) / 2;
        
        // JFrame background black
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        // drawing JPanel
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, scaledWidth, scaledHeight);
	}
}
