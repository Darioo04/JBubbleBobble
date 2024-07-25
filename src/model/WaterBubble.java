package model;

public class WaterBubble extends Bubble {
//fa cadere una cascata nella direzione opposta al pg
	private static Water water = Water.getInstance();
	
	public WaterBubble(int x,int y) {
		super(x,y);
	}
	
	@Override
	public void shot() {
		
	}
}
