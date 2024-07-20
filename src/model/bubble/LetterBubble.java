package model.bubble;

public class LetterBubble extends Bubble {
//donano una vita extra se viene composta la parola "EXTENDS"
	private char letter;
	
	public LetterBubble(int x,int y,char letter) {
		super(x,y);
		this.letter=letter;
	}
	
	
	@Override
	public void shot() {
		
	}
}
