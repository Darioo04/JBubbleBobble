package model;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.imageio.ImageIO;

import view.FontView;

@SuppressWarnings("deprecation")

public class FontModel extends Observable {
	private Map<Character,BufferedImage> font;
	private final String path = "/sprites/fontChars/font-";
	private static FontModel instance;
	
	private FontModel() {
		font = new HashMap<>();
		initFont();
		setChanged();
		notifyObservers();
	}
	
	public static FontModel getInstance() {
		if (instance==null) instance = new FontModel();
		return instance;
	}
	
	public void initFont() {
		
		//Aggiungo alla Map le lettere
		for (char s='A'; s<'Z'; s++) {
			try {
				BufferedImage character = ImageIO.read(getClass().getResource(path+s+".png"));
				font.put(s, character);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Aggiungo alla Map i numeri
		for (char n='1'; n<'9'; n++) {
			try {
				BufferedImage character = ImageIO.read(getClass().getResource(path+n+".png"));
				font.put(n, character);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Aggiungo alla Map il punto esclamativo
		try {
			BufferedImage character = ImageIO.read(getClass().getResource(path+"!"+".png"));
			font.put('!', character);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Character, BufferedImage> getFont() {
		return font;
	}
	
	
}
