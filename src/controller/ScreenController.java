package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.MenuScreen;
import model.StateScreen;

public class ScreenController implements KeyListener {
	private static ScreenController instance;
	private MenuScreen menuScreen = MenuScreen.getInstance()
;	
	public static ScreenController getInstance() {
		if (instance==null) instance = new ScreenController();
		return instance;
	}
	
//	private ScreenController(StateScreen stateScreen) {
//		this.stateScreen=stateScreen;
//	}
	
	@Override
	public void keyTyped(KeyEvent k) {
		
	}
	
	public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
        if (key==KeyEvent.VK_UP && menuScreen.getPointer()>0) {
        	menuScreen.decreasePointer();
        }
        if (key==KeyEvent.VK_DOWN && menuScreen.getPointer()<menuScreen.getNumOptions()) {
        	menuScreen.increasePointer();
        }

	}
	
	public void keyReleased(KeyEvent k) {
		
	}
}
