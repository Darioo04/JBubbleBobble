package view;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import model.GameConstants;
import model.StateScreen;

@SuppressWarnings("deprecation")

public abstract class StateScreenView extends JPanel implements Observer {
	
	private Image screenToDisplay;
	private boolean isThereKeyController;
	
	protected StateScreenView() {
        this.setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

    }
	
	public Image getScreenToDisplay() {
        return screenToDisplay;
    }
	
	public void setImage(Image screenToDisplay) {
        this.screenToDisplay = screenToDisplay;
    }
	
	public void setScreenToDisplay(Image screenToDisplay) {
        this.screenToDisplay = screenToDisplay;
    }
	
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(screenToDisplay, 0, 0, this.getWidth(), this.getHeight(), null);
    }

	@Override
	public void update(Observable o, Object arg) {
		StateScreen stateScreen = (StateScreen) o;
		this.setImage(stateScreen.getScreens()[stateScreen.getPointer()]);
        this.repaint();
		
	}
	
	public boolean isThereKeyController() {
        return isThereKeyController;
    }
	
	public void setThereKeyController(boolean isThereKeyController) {
        this.isThereKeyController = isThereKeyController;
    }

}
