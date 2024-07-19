package view;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public static GameFrame instance;
	
	public static GameFrame getInstance() {
        if(instance == null) instance = new GameFrame();
        return instance;
    }
	
	private GameFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
	}

}
