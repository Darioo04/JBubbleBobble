package model.bubble;

import java.util.Observable;
@SuppressWarnings("deprecation")
public abstract class Bubble extends Observable  {
	protected final int WIDTH = 600;
	protected final int HEIGHT = 400;
	protected final int RADIUS = 40;
	protected int x;
	protected int y;
	
	public Bubble(int x,int y) {
		this.x=x;
		this.y=y;
		
	}
	public abstract void shot();
		
	
}
