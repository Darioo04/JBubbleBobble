package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtendBubble extends Bubble {
	/*
	 * donano una vita extra se viene composta la parola "EXTENDS"
	 * per identificare ogni lettera viene usato un enum
	 */
	
	
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
		if (letters.size()==0) createLetterList();
		getRandomLetter();
		setNumSprites(1);
		setPath("extend"+numLetter.position+"-");
	}
	
	//raccoglie tutte le lettere e le aggiunge ad una lista
	private void createLetterList() { 
		letters = Stream.of(NumLetter.values()).collect(Collectors.toList());
	}
	
	private void getRandomLetter() {
		int index = new Random().nextInt(letters.size());
		numLetter = letters.get(index);
	}
	
	public void deleteLetter() {
		if (letters.contains(numLetter)) letters.remove(numLetter);
		if (letters.size()==0) {
			createLetterList();
			Player.getInstance().increaseLives();
		}
	}
}
