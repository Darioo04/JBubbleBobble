package view;


public class WinScreenView extends StateScreenView {
	private static WinScreenView instance;
	
	public static WinScreenView getInstance() {
		if (instance==null) instance = new WinScreenView();
		return instance;
	}
	
	private WinScreenView() {
			
	}
}
