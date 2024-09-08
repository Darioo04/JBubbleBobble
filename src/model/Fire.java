package model;

@SuppressWarnings("deprecation")

public class Fire extends ObjModel {
	
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
			
		}
		setChanged();
		notifyObservers();
	}
}

