package model;

@SuppressWarnings("deprecation")

public class FireBall extends ObjModel {
	
	private Direction direction;
	
	public FireBall(int x, int y, Direction direction) {
		super(x,y);
		setPath("/sprites/Obj/fireball-");
		this.direction = direction;
	}
	
	@Override
	public void update() {
		int x = getX();
		if (x < GameConstants.SCREEN_WIDTH && x>0) {
			switch(direction) {
				case LEFT -> {
					setX( x - GameConstants.BUBBLE_X_SPEED );
				}
				
				case RIGHT -> {
					setX( x + GameConstants.BUBBLE_X_SPEED );
				}
				
				default -> {
					
				}
			}
		}
		else {
			setCanBeDeleted(true);
		}
		setChanged();
		notifyObservers();
	}
}
