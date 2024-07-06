package model;

public abstract class Entity {
	
	protected int hp;
	protected int x, y;
	
	public Entity(int hp, int x, int y) {
		this.hp = hp;
		this.x = x;
		this.y = y;
	}

}