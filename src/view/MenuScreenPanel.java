package view;

public class MenuScreenPanel extends StateScreenPanel {
	private static MenuScreenPanel instance;
	
	public static MenuScreenPanel getInstance() {
        if (instance == null) instance = new MenuScreenPanel();
        return instance;
    }
	
	private MenuScreenPanel() {
        super();
    }
}
