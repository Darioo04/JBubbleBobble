package model;

public class Invader extends Enemy {
	protected String filePath;
	
	public Invader() {
		super(25,25,"Invader");
		this.filePath = "JBubbleBobble/res/sprites/invader/image_1.png";
	}
}
