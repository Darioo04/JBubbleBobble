package model;

@SuppressWarnings("deprecation")
public class Monsta extends Enemy {
	public Monsta(int x, int y) {
		super(x,y);
		setDirection(Direction.RIGHT);
		setPath("/sprites/Monsta/");
		setSpeed(4);
	}
	@Override
    public void update() {
		collisionChecker.checkTileCollision(this);
		int speed = getSpeed();
		switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed) {
					x += speed;
				}
			}
			
			case LEFT -> {
				if (x > 2*GameConstants.TILE_SIZE + speed) {
	                x -= speed;
	            }
			}
			
			default ->{}
		}
		setChanged();
        notifyObservers();
	
    }
}
	