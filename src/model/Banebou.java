package model;

@SuppressWarnings("deprecation")

public class Banebou extends Enemy {
	//si muovono saltando ad arco
	private static final int JUMP_STRENGHT = 4;
	public Banebou(int x, int y) {
		super(x,y);
		setPath("/sprites/banebou/");
		setDirection(Direction.RIGHT);
		setSpeed(4);
	}
	
	@Override
	public void update() {
		super.update();
		if (Math.random() < 0.03) { // 10% di probabilità di cambiare direzione
            randomizeDirection(); 
        }
		int speed = getSpeed();
		switch (getDirection()) {
			case RIGHT -> {
				if (x < GameConstants.SCREEN_WIDTH - 3*GameConstants.TILE_SIZE - speed) {
					x += speed;
					y += JUMP_STRENGHT;
				}
				else {
					randomizeDirection();
				}
			}
			
			case LEFT -> {
				if (x > 2*GameConstants.TILE_SIZE + speed) {
	                x -= speed;
	                y += JUMP_STRENGHT;
	            }
				else {
					randomizeDirection();
				}
			}
			
			default ->{}
		}
		setChanged();
        notifyObservers();
	}
	
	private void randomizeDirection() {
        setDirection((Math.random()<=0.5) ? Direction.LEFT : Direction.RIGHT);
	}
}
