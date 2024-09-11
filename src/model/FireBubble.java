package model;

public class FireBubble extends Bubble {
//bolle contenenti delle fiamme faranno cadere a terra una fiamma 
	
	public FireBubble(int x, int y) {
		super(x,y);
		setExpanded(true);
		setFloating(true);
		setPath("fire-");
		setNumSprites(3);
	}
}
