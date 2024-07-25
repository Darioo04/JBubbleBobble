package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.StateScreen;

public class ScreenController implements KeyListener {
	private static ScreenController instance;
	private StateScreen stateScreen;
	
	public static ScreenController getInstance(StateScreen stateScreen) {
		if (instance==null) instance = new ScreenController(stateScreen);
		return instance;
	}
	
	private ScreenController(StateScreen stateScreen) {
		this.stateScreen=stateScreen;
	}
	
	@Override
	public void keyTyped(KeyEvent k) {
		
	}
	
	public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
        if (key==KeyEvent.VK_UP && stateScreen.getPointer()>0) {
        	stateScreen.decreasePointer();
        }
        if (key==KeyEvent.VK_DOWN && stateScreen.getPointer()<stateScreen.getNumOptions()) {
        	stateScreen.increasePointer();
        }

	}
	
	public void keyReleased(KeyEvent k) {
		
	}
}
