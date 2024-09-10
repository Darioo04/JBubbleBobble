package model;

@SuppressWarnings("deprecation")

public class Fire extends ObjModel {
	
	private int fireCounter;
	private static final int FIRE_DELAY = 360;
	
	public Fire(int x, int y) {
		super(x,y);
		setPath("/sprites/Obj/fire-");
		setNumSprites(3);
	}
	
	public void update() {
		CollisionChecker.getInstance().checkTileCollision(this);
		if (!getCollisionDown()) {
			setY( getY() + GameConstants.BUBBLE_X_SPEED);
		}
		else if (getCollisionDown()) {
			if (fireCounter++ >= FIRE_DELAY) {
				setCanBeDeleted(true);
			}
		}
		setChanged();
		notifyObservers();
	}
}

