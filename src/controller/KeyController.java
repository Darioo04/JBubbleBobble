package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameState;
import model.MenuScreen;
import model.Player;
import model.SelectLevelScreen;
import view.MenuScreenView;

public class KeyController implements KeyListener {
	
	private static KeyController instance;
	
	private Player player;
	private GameController gameController;
	private MenuScreen menuScreen = MenuScreen.getInstance();
	private SelectLevelScreen selectLevelScreen = SelectLevelScreen.getInstance();
	
	public static KeyController getInstance() {
		if (instance == null) instance = new KeyController();
        return instance;
    }
	
    private KeyController() {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	gameController = GameController.getInstance();    	
        int key = e.getKeyCode();
        
        switch (gameController.getGameState()) {
		case MENU:
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
	        	menuScreen.decreasePointer();
	        }
	        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
	        	menuScreen.increasePointer();
	        }
	        if (key == KeyEvent.VK_ENTER) {
	        	if (menuScreen.getPointer() == 0) {
	        		gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), selectLevelScreen.getStateScreenView());
	        		gameController.setGameState(GameState.SELECT_LEVEL);
	        		selectLevelScreen.update();
	        	}
	        }
	        
			break;
			
		case SELECT_LEVEL:
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
	        	selectLevelScreen.decreasePointer();
	        }
	        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
	        	selectLevelScreen.increasePointer();
	        }	
	        	
	        break;

		default:
			break;
		}
        
//        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
//            player.move(-5, 0);
//        }
//        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
//            player.move(5, 0);
//        }
//        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
//            player.move(0, -5);
//        }
//        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
//            player.move(0, 5);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
