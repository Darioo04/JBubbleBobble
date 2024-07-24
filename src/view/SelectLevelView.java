package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SelectLevelView extends JFrame implements Observer {
//	private static SelectLevelView instance;
//	
//	public static SelectLevelView getInstance() {
//		if (instance==null) instance = new SelectLevelView();
//		return instance;
//	}
	
	public SelectLevelView() {
		super("Select Level Screen");
		setBackground(Color.BLACK);
		setVisible(true);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new JPanel(new GridLayout(6,4,10,10)) {
			{
				setBackground(Color.BLACK);
				setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
				
				for (int level=1; level<=24; level++) {
					add(new JButton("Level "+level) {
						{
							setBackground(Color.BLACK);
							setForeground(Color.WHITE);
							setFocusPainted(false);
							setFont(new Font("",Font.BOLD,30));
							addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									
								}
							});
						}
					});
				}
			}
		});
	}
	
	@Override
	public void update(Observable o,Object arg) {
		
	}
	
	
	public static void main(String[] args) {
		new SelectLevelView();
	}
}
