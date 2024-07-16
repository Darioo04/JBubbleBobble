package model.bubble;

public class LetterBubble extends Bubble {
//donano una vita extra se viene composta la parola "EXTENDS"
	private char letter;
	private String word;
	
	public LetterBubble(int x,int y,char letter) {
		super(x,y);
		this.letter=letter;
	}
	
	public boolean addLetter(char letter) {
		this.word+=letter;
		if (word.equals("EXTENDS")) return true;
		else return false;
	}
	
	@Override
	public void shot() {
		
	}
}
