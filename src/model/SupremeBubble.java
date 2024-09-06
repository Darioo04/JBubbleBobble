package model;

public class SupremeBubble extends Bubble {
//bolla molto rara rossa con un fulmine lampeggiante donerà 100.000 punti al giocatore 
//e abiliterà il personaggio principale a sputare palle di fuoco per i successivi 6 livelli.
	
//	private final long BONUS=100000;
//	private final int COUNTDOWN=6;
	
	public SupremeBubble(int x,int y) {
		super(x,y);
		setExpanded(true);
		setFloating(true);
		setPath("supreme-");
		setNumSprites(1);
	}
}
