package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import model.FontModel;

public class FontView extends JLabel {
	private String text;
	private static Map<Character,BufferedImage> fontChars = FontModel.getInstance().getFont();
	
	public FontView(String text) {
		this.text=text.toUpperCase();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFont(g);
	}
	
	protected void drawFont(Graphics g) {
		char[] charArray = text.toCharArray();
		for (char c : charArray) {
			if (fontChars.containsKey(c)) {
//				BufferedImage image = font.get(c);
//				add(image);
			}
		}
	}
}
