package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExtendBubble extends Bubble {
//donano una vita extra se viene composta la parola "EXTENDS"
	private int numLetter;
	private static List<Integer> letters = new ArrayList<>();
	
	public ExtendBubble(int x,int y) {
		super(x,y);
		setExpanded(true);
		setFloating(true);
		if (letters.size()==0) createLetterArray();
		getRandomLetter();
		setNumSprites(1);
		setPath("extend"+numLetter+"-");
	}
	
	private void createLetterArray() {
		for (int i=1; i<=6; i++) {
			letters.add(i);
		}
	}
	
	private void getRandomLetter() {
		int index = new Random().nextInt(letters.size());
		numLetter = letters.get(index);
	}
	
	public void deleteLetter() {
		if (letters.get(numLetter)!=null) letters.remove(numLetter);
		if (letters.size()==0) {
			createLetterArray();
			Player.getInstance().increaseLives();
		}
	}
}
