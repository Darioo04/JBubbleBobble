package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Level;
import model.SelectLevelScreen;
import model.StateScreen;
import model.Tiles;
public class LevelPanel extends StateScreen {
	private SelectLevelScreen levelScreen = SelectLevelScreen.getInstance();
	public LevelPanel() {
		Tiles[][] level = new Level(levelScreen.getPointer()+1).getLevel();
//		add(new JPanel(new GridBagLayout()) {
//			{
//				for (int i=0; i<level.length; i++) {
//					for (int k=0; k<level[0].length; k++) {
//						try {
//							BufferedImage image = ImageIO.read(getClass().getResource(level[i][k].getPath()));
//							ImageIcon newImage = new ImageIcon(image);
//							add(new JLabel(newImage));
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		});
		
	}
	
}
