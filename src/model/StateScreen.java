package model;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import view.StateScreenView;

@SuppressWarnings("deprecation")

public abstract class StateScreen extends Observable {
	
	private String sourceFolder;
	private String fileName;
	private StateScreenView stateScreenView;
	private Image[] sequence;
	private int pointer;
	
	protected void loadScreens(int numOptions) {
		//carica le schermate in base al numero di opzioni
		sequence = new Image[numOptions];
		for (int i = 1; i <= sequence.length; i++) {
			try {
				String path = sourceFolder + fileName + i + ".png";
				sequence[i-1] = ImageIO.read(getClass().getResourceAsStream(path)).getScaledInstance(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT, Image.SCALE_DEFAULT);
			} catch (Exception e) {
				System.err.println("Error loading screen: " + fileName + i + ".png");
			    e.printStackTrace();
			}
		}
	}
	
	public void increasePointer() {
		if (pointer < sequence.length-1) {
			pointer++;
		}
		else {
			pointer = 0;
		}
		update();
	}
	
	public void decreasePointer() {
		if (pointer > 0) {
            pointer--;
        }
        else {
            pointer = sequence.length-1;
        }
        update();
	}
	
	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
	
	public int getPointer() {
		return pointer;
	}
	
	public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	
	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
    }

	public void update() {
		setChanged();
        notifyObservers();
	}
	
	public void setStateScreenView(StateScreenView stateScreenView) {
        this.stateScreenView = stateScreenView;
        this.addObserver(stateScreenView);
    }
}
