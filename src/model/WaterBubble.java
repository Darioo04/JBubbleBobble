package model;

public class WaterBubble extends Bubble {
//fa cadere una cascata nella direzione opposta al pg
	
	public WaterBubble(int x,int y) {
		super(x,y);
		setExpanded(true);
		setFloating(true);
		setPath("water-");
		setNumSprites(3);
	}
	
}
