package model.bubble;

import model.obj.Water;

public class WaterBubble extends Bubble {
//fa cadere una cascata nella direzione opposta al pg
	private Water water;
	
	public WaterBubble(int x,int y) {
		super(x,y);
	}
	
	@Override
	public void shot() {
		
	}
}
