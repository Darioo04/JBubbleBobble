package model;

public class Fire {
	
	private Direction direction;
	private int x;
	private int y;
	
	public Fire(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void update() {
		switch(direction) {
			case RIGHT -> {
				
			}
			case LEFT -> {
				
			}
		}
	}
}

