package view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.EmptyBlock;
import model.Level;
import model.SelectLevelScreen;
import model.Tiles;
import model.Wall;

public class LevelPanel extends JPanel implements Observer {
	private Tiles[][] level;
	
	public LevelPanel(Tiles[][] level) {
		this.level=level;
		this.setVisible(true);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLevel((Graphics2D) g);
	}
	
	public void loadTiles() {
		
	}
	
	public void drawLevel(Graphics2D g) {
		int size=20;
		int y=0;
		for (int i=0; i<level.length; i++) {
			int x=0;
			for (int k=0; k<level[0].length; k++) {
				if (level[i][k] instanceof Wall) {
					try {
						BufferedImage image = ImageIO.read(getClass().getResource(level[i][k].getPath()));
						g.drawImage(image, x, y-image.getHeight(), null );
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				x+=size;
			}
			y+=size;
		}
	}
	
	public void update(Observable o,Object arg) {
		
	}
}