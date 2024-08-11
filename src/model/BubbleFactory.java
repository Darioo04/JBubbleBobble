package model;

public class BubbleFactory {
	private static BubbleFactory instance;
	private Player player;
	
	public static BubbleFactory getInstance() {
		if (instance==null) instance = new BubbleFactory();
		return instance;
	}
	
	private BubbleFactory() {
		player=Player.getInstance();
	}
	
	public Bubble createBubble(int perc) { //chiami il riferimento BubbleFactory::createBubble
		 
		if (perc<=1) return new SupremeBubble(player.getX(),player.getY());
		else if (perc<=8) return new LightningBubble(player.getX(),player.getY());
		else if (perc<=20) return new FireBubble(player.getX(),player.getY());
		else if (perc<=30) return new WaterBubble(player.getX(),player.getY());
		
		else return null;
	}
}
