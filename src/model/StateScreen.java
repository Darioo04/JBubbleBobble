package model;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import view.StateScreenView;

@SuppressWarnings("deprecation")

public abstract class StateScreen extends Observable {
	private int numOptions;
	private String sourceFolder;
	private String fileName;
	private StateScreenView stateScreenView;
	private Image[] screens;
	private int pointer;
	
	protected void loadScreens() {
		//carica le schermate in base al numero di opzioni
		this.sourceFolder = "/screens/";
		screens = new Image[numOptions];
		for (int i = 1; i <= screens.length; i++) {
			try {
				String path = sourceFolder + fileName + i + ".png";
				screens[i-1] = ImageIO.read(getClass().getResourceAsStream(path)).getScaledInstance(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT, Image.SCALE_DEFAULT);
			} catch (Exception e) {
				System.err.println("Error loading screen: " + fileName + i + ".png");
			    e.printStackTrace();
			}
		}
	}
	
	public void increasePointer() {
		if (pointer < screens.length-1) {
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
            pointer = screens.length-1;
        }
        update();
	}
	
	public void setNumOptions(int numOptions) {
		this.numOptions=numOptions;
	}
	
	public int getNumOptions() {
		return numOptions;
	}
	
	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
	
	public int getPointer() {
		return pointer;
	}
	
	public Image[] getScreens() {
        return screens;
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
	
	public StateScreenView getStateSreenView() {
		return stateScreenView;
	}
}
