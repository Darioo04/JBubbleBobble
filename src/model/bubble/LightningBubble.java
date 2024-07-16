package model.bubble;

import model.obj.Lightning;

public class LightningBubble extends Bubble {
// quando scoppiata rilascia un fulmine
	private Lightning lightning;
	
	public LightningBubble(int x, int y) {
		super(x,y);
	}
	
	@Override 
	public void shot() {
		
	}
}
