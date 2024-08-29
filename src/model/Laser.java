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
		Rectangle playerHitbox = Player.getInstance().getHitbox();
		if (getY()<GameConstants.SCREEN_HEIGHT) {
			setY( getY() + GameConstants.BUBBLE_X_SPEED);
			if (getHitbox().intersects(playerHitbox)) {
				Player.getInstance().decreaseLives();
			}
		}
		setChanged();
		notifyObservers();
	}
}
