package model;

public class LightningBubble extends Bubble {
// quando scoppiata rilascia un fulmine
	private static Lightning lightning = Lightning.getInstance();
	
	public LightningBubble(int x, int y) {
		super(x,y);
	}
	
	@Override 
	public void shot() {
		
	}
}
