package model.bonus;

public abstract class PointItem  {
	protected long bonus;
	
	public PointItem(long bonus) {
		this.bonus=bonus;
	}
	
	public long getBonus() {
		return bonus;
	}
}
