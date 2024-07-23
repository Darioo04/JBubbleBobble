package view;

import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("deprecation")


public class MenuPanel extends JPanel implements Observer {
	private static MenuPanel instance;
	
	public static MenuPanel getInstance() {
		if (instance==null) instance = new MenuPanel();
		return instance;
	}
	
	public MenuPanel() {
		super(new GridLayout(14, 16));
		setBackground(Color.BLACK);
		setVisible(true);
		add(new JPanel(new BorderLayout()) {
			{
				add(new JPanel() {
					{
						JButton ritorno = new JButton("Main");
						ritorno.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									
								}
						});
					}
				});
			}
		});
	}
	
	@Override
	public void update(Observable o,Object arg) {
		
	}
}
