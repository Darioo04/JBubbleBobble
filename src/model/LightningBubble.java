package model;

public class LightningBubble extends Bubble {
// quando scoppiata rilascia un fulmine
	private Lightning lightning = Lightning.getInstance();
	
	public LightningBubble(int x, int y) {
		super(x,y);
	}
	
	@Override 
	public void update() {
		
	}
}
