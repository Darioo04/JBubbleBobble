package model;

public class FireBubble extends Bubble {
//bolle contenenti delle fiamme faranno cadere a terra un tappeto infuocato 
//che tramuterà tutti i nemici con cui entrerà in contatto in diamanti rossi da 9000 punti.
	private Fire fire;
	
	public FireBubble(int x, int y) {
		super(x,y);
		this.fire= new Fire();
	}
	
	@Override
	public void shot() {
		
	}
}
