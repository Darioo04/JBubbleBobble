package model;

public class Invader extends Enemy {
	protected String filePath;
	
	public Invader() {
		super(25,25,"Invader");
		this.filePath = "/sprites/invader/";
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
}
