package model;

import java.awt.Rectangle;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class Laser extends ObjModel {
	
	public Laser(int x, int y) {
		super(x,y);
		setPath("/sprites/invader/laser-");
		setNumSprites(2);
	}
	
	@Override
	public void update() {
		if (getY()<GameConstants.SCREEN_HEIGHT) {
			setY( getY() + GameConstants.OBJECT_FALLING_SPEED);
		}
		else {
			setCanBeDeleted(true);
		}
		if (getHitbox().intersects(Player.getInstance().getHitbox())) {
			Player.getInstance().decreaseLives();
		}
		
		setChanged();
		notifyObservers();
	}
}
