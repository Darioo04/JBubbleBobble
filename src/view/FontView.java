package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.FontCreator;

public class FontView extends JPanel {
	private String text;
	private static Map<Character,String> fontChars = FontCreator.getInstance().getFont();
	
	public FontView(String text) {
		this.text=text.toUpperCase();
		setLayout(new BorderLayout());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFont(g);
	}
	
	protected void drawFont(Graphics g) {
		
		int x=15; //posizione dei caratteri
		int y=30;
		
		char[] charArray = text.toCharArray();
		for (char c : charArray) {
			if (fontChars.containsKey(c)) {	
				try {
					BufferedImage image = ImageIO.read(getClass().getResource(fontChars.get(c)));
					g.drawImage(image, x, y-image.getHeight(), null);
					x+=image.getWidth() + 5;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (c==' ') { x+=15; }
			
		}
	}
	
//	@Override
//	public void update(Observable o,Object arg) {
//		repaint();
//	}
}
