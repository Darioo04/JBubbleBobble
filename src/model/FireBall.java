package model;

public class FireBall extends ObjModel {
	
	private Direction direction;
	
	public FireBall(int x, int y, Direction direction) {
		super(x,y);
		setPath("/sprites/Obj/fireball");
		this.direction = direction;
	}
	
	@Override
	public void update() {
		switch (direction) {
			case LEFT -> {
				setX( getX() - GameConstants.BUBBLE_X_SPEED );
			} 
			case RIGHT -> {
				setX( getX() + GameConstants.BUBBLE_X_SPEED );
			}
			
			default -> {
				
			}
		}
		super.update();
	}
}
