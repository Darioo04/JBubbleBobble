package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.GameConstants;

public class StateScreenView extends JPanel {
	
	protected StateScreenView() {
        this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBackground(Color.black);

    }

}
