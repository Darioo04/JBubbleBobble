package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Player;

public class PlayerView extends JPanel{
	
	private Player player;
	private BufferedImage playerImage;
//	private static PlayerView instance;
	
	public PlayerView(Player player) {
        this.player = player;
        try {
        	playerImage = ImageIO.read(getClass().getResourceAsStream("/sprites/BubAndBob1/Bub-0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
//	public static PlayerView getInstance(Player player) {
//		if (instance==null) instance = new PlayerView(player);
//		return instance;
//	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerImage != null) {
            g.drawImage(playerImage, player.getX(), player.getY(), null);
        }
    }

}
