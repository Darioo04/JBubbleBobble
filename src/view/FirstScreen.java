package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstScreen extends JFrame{
	
	JPanel panel = new JPanel();
	
	public FirstScreen() {
		super("JBubble Bobble");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setBackground(Color.BLACK);
    }
    
	
	
}
