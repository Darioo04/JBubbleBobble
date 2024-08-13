package view;


import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontCreator {
	private static FontCreator instance;
	private Font font;
	
	public static FontCreator getInstance() {
		if (instance==null) instance = new FontCreator();
		return instance;
	}
	
	private FontCreator() {
		try {
			String path = System.getProperty("user.dir")+"/res/Font/BubbleBobble-font.ttf";
//			System.out.println(path);
			font = Font.createFont(Font.TRUETYPE_FONT,new File(path));
			font = font.deriveFont(16f);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public Font getFont() {
		return font;
	}
}
