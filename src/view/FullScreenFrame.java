package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FullScreenFrame extends JFrame{
	
	JPanel panel = new JPanel();
	
	public FullScreenFrame() {
		super("JBubble Bobble");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }
    
	
	
}
