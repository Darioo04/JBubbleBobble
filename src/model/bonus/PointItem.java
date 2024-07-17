package model.bonus;
import java.util.Observable;

@SuppressWarnings("deprecation")


public abstract class PointItem extends Observable {
	protected long bonus;
	
	public PointItem(long bonus) {
		this.bonus=bonus;
	}
	
	public long getBonus() {
		return bonus;
	}
}
