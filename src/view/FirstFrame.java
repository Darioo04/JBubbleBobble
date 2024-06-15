package view;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class FirstFrame extends JFrame {
	
	public FirstFrame() {
		super("JBubbleBobble");
		
//		Toolkit tk=Toolkit.getDefaultToolkit();
//		Dimension screenSize = tk.getScreenSize();
		
//		this.setSize(256, 224);
		
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
