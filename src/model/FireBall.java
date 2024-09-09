package model;

@SuppressWarnings("deprecation")

public class FireBall extends ObjModel {
	
	private Direction direction;
	
	public FireBall(int x, int y, Direction direction) {
		super(x,y);
		setPath("/sprites/Obj/fireball-");
		setNumSprites(2);
		this.direction = direction;
	}
	
	@Override
	public void update() {
		CollisionChecker.getInstance().checkPlayerFireBallCollision(Player.getInstance(), this);
		int x = getX();
		if (x < GameConstants.SCREEN_WIDTH && x>0) {
			switch(direction) {
				case LEFT -> {
					setX( x - GameConstants.OBJECT_SPEED);
				}
				
				case RIGHT -> {
					setX( x + GameConstants.OBJECT_SPEED );
				}
				
				default -> {
					
				}
			}
		}
		else {
			setCanBeDeleted(true);
		}
		updateHitbox();
		setChanged();
		notifyObservers();
	}
}
