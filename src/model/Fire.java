package model;

public class Fire {
	
	private Direction direction;
	private int x;
	private int y;
	
	public Fire(Bubble bubble,Direction direction) {
		this.direction = direction;
		this.x = bubble.getX();
		this.y = bubble.getY();
		
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

