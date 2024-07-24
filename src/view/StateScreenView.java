package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.GameConstants;

public class StateScreenView extends JPanel implements Observer {
	
	private Image screenToDisplay;
	
	protected StateScreenView() {
        this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
