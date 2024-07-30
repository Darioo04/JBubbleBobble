package controller;


import java.util.HashMap;
import java.util.Map;

public class FontCreator {
	private Map<Character,String> font;
	private final String path = "/sprites/fontChars/font-";
	private static FontCreator instance;
	
	public static FontCreator getInstance() {
		if (instance==null) instance = new FontCreator();
		return instance;
	}
	
	private FontCreator() {
		font = new HashMap<>();
		initFont();
	}

	public void initFont() {
		for (char s='A'; s<='Z'; s++) {
			font.put(s, path+s+".png");
		}
		for (char n='1'; n<='9';n++) {
			font.put(n, path+n+".png");
		}
		font.put('!', path+"!.png");
	}

	public Map<Character, String> getFont() {
		return font;
	}
}
