package model;

public class ThunderBubble extends Bubble {
// quando scoppiata rilascia un fulmine
	
	public ThunderBubble(int x, int y) {
		super(x,y);
		setExpanded(true);
		setFloating(true);
		setPath("thunder-");
		setNumSprites(3);
	}
	
}
