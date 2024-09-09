package model;

@SuppressWarnings("deprecation")

public class Thunder extends ObjModel {
	
	private Direction direction;
	
	public Thunder(int x, int y, Direction direction) {
		super(x, y);
		this.direction = direction;
		setPath("/sprites/Obj/thunder-");
		setNumSprites(3);
	}
	
	@Override
	public void update() {
		int x = getX();
		if (x < GameConstants.SCREEN_WIDTH && x> 1*GameConstants.TILE_SIZE + GameConstants.SCALE) {
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
