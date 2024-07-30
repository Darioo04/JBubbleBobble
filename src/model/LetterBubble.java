package model;

public class LetterBubble extends Bubble {
//donano una vita extra se viene composta la parola "EXTENDS"
	private int numLetter;
	
	public LetterBubble(int x,int y,int numLetter) {
		super(x,y);
		this.numLetter=numLetter;
	}
	
	
	@Override
	public void shot() {
		
	}
}
