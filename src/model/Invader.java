package model;

public class Invader extends Enemy {
	protected String filePath;
	
	public Invader() {
		super(25,25,"Invader");
		this.setPath("/sprites/invader/");
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFallingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
}
