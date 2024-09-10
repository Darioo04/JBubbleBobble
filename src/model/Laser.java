package model;

@SuppressWarnings("deprecation")

public class Laser extends ObjModel {
	
	public Laser(int x, int y) {
		super(x,y);
		setPath("/sprites/invader/laser-");
		setNumSprites(2);
	}
	
	@Override
	public void update() {
		CollisionChecker.getInstance().checkPlayerLaserCollision(Player.getInstance(), this);
		if (getY()<GameConstants.SCREEN_HEIGHT) {
			setY( getY() + GameConstants.OBJECT_SPEED);
		}
		else {
			setCanBeDeleted(true);
		}
		updateHitbox();
		setChanged();
		notifyObservers();
	}
}
