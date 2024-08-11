package controller;


import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontCreator {
	private Font font;
//	private final String path = "/sprites/fontChars/font-";
	private static FontCreator instance;
	
	public static FontCreator getInstance() {
		if (instance==null) instance = new FontCreator();
		return instance;
	}
	
	private FontCreator() {
		try {
			String path = System.getProperty("user.dir")+"/res/sprites/BubbleBobble-font.ttf";
//			System.out.println(path);
			font = Font.createFont(Font.TRUETYPE_FONT,new File(path));
			font = font.deriveFont(12f);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
//		initFont();
	}

//	public void initFont() {
//		
//		for (char s='A'; s<='Z'; s++) {
//			font.put(s, path+s+".png");
//		}
//		for (char n='1'; n<='9';n++) {
//			font.put(n, path+n+".png");
//		}
//		font.put('!', path+"!.png");
//	}

	public Font getFont() {
		return font;
	}
}
