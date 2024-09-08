package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExtendBubble extends Bubble {
//donano una vita extra se viene composta la parola "EXTENDS"
	
	private enum NumLetter{
		FIRST(1),
		SECOND(2),
		THIRD(3),
		FOURTH(4),
		FIFTH(5),
		SIXTH(6);
		
		int position;
		
		NumLetter(int position) {
			this.position = position;
		}
	}
	
	private NumLetter numLetter;
	private static List<NumLetter> letters = new ArrayList<>();
	
	public ExtendBubble(int x,int y) {
		super(x,y);
		setExpanded(true);
		setFloating(true);
		if (letters.size()==0) createLetterArray();
		getRandomLetter();
		setNumSprites(1);
		setPath("extend"+numLetter.position+"-");
	}
	
	private void createLetterArray() {
		for (NumLetter nl : NumLetter.values()) {
			letters.add(nl);
		}
	}
	
	private void getRandomLetter() {
		int index = new Random().nextInt(letters.size());
		numLetter = letters.get(index);
	}
	
	public void deleteLetter() {
		if (letters.contains(numLetter)) letters.remove(numLetter);
		if (letters.size()==0) {
			createLetterArray();
			Player.getInstance().increaseLives();
		}
	}
}
