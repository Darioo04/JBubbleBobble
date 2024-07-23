package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.FontModel;

public class FontView extends JLabel implements Observer {
	private String text;
	private static Map<Character,BufferedImage> fontChars = FontModel.getInstance().getFont();
	
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
		Graphics2D g2d = (Graphics2D) g;
		
		int x=15; //posizione dei caratteri
		int y=30;
		
		char[] charArray = text.toCharArray();
		for (char c : charArray) {
			if (fontChars.containsKey(c)) {	
				BufferedImage image = fontChars.get(c);
				g2d.drawImage(image, x, y-image.getHeight(), null);
				x+=image.getWidth() + 5;
			}
			else if (c==' ') { x+=15; }
			y+=20;
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
