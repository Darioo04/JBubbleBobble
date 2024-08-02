package view;


public class SelectLevelPanel extends StateScreenPanel {
	
	private static SelectLevelPanel instance;
	
	public static SelectLevelPanel getInstance() {
		if (instance==null) instance = new SelectLevelPanel();
		return instance;
	}
	
	private SelectLevelPanel() {
		super();
		
	}
	
	
}
