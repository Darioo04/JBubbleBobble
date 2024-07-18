package model.tiles;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class EmptyBlock extends Tiles {
	
	public EmptyBlock() {
		this.collision=false;
		JPanel panel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.setColor(Color.BLACK);
			}
		};
		panel.setVisible(true);
	}
	
	
}
