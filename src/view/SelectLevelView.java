package view;


public class SelectLevelView extends StateScreenView {
	
	private static SelectLevelView instance;
	
	public static SelectLevelView getInstance() {
		if (instance==null) instance = new SelectLevelView();
		return instance;
	}
	
	private SelectLevelView() {
		super();
		
	}
	
	
}
