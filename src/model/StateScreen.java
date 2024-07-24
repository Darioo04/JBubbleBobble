package model;

import java.util.Observable;
import java.util.Observer;

import view.StateScreenView;

@SuppressWarnings("deprecation")

public class StateScreen extends Observable {
	
	private StateScreenView stateScreenView;

	public void update() {
		setChanged();
        notifyObservers();
	}
	
	public void setStateScreenView(StateScreenView stateScreenView) {
        this.stateScreenView = stateScreenView;
        this.addObserver(stateScreenView);
    }
}
