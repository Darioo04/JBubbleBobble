package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameState;
import model.GamingScreen;
import model.MenuScreen;
import model.Player;
import model.SelectLevelScreen;
import model.SelectProfileScreen;
import view.MenuScreenView;

public class KeyController implements KeyListener {
	
	private static KeyController instance;
	
	private Player player;
	private GameController gameController;
	private MenuScreen menuScreen = MenuScreen.getInstance();
	private SelectLevelScreen selectLevelScreen = SelectLevelScreen.getInstance();
//	private SelectProfileScreen selectProfileScreen = SelectProfileScreen.getInstance();
	private GamingScreen gamingScreen = GamingScreen.getInstance();
	
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
			case MENU -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					menuScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					menuScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					switch (menuScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), selectLevelScreen.getStateScreenView());
							gameController.setGameState(GameState.SELECT_LEVEL);
							selectLevelScreen.update();
						}
						case 1 -> {
							gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), selectLevelScreen.getStateScreenView());
							gameController.setGameState(GameState.SELECT_LEVEL);
							selectLevelScreen.update();
						}
						case 2 -> {
							gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), selectLevelScreen.getStateScreenView());
							gameController.setGameState(GameState.SELECT_LEVEL);
							selectLevelScreen.update();
						}
						default -> {
							
						}
					}
				}
			}
			
			case SELECT_LEVEL -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					selectLevelScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					selectLevelScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					gameController.changeDisplayedScreen(selectLevelScreen.getStateScreenView(), gamingScreen.getStateScreenView());
					gameController.setGameState(GameState.GAME);
					gamingScreen.loadLevel();
					gamingScreen.update();
				}
			}
			
			case LOGIN -> {
				
			}
				
			case GAME -> {
				//player controller
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					player.move(0,5);
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					player.move(0,-5);
				}
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					player.move(-5,0);
				}
				if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					player.move(5,0);
				}
			}
			
			case PAUSE -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					selectLevelScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					selectLevelScreen.increasePointer();
				}	
			}
			
			case SELECT_PROFILE -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					selectLevelScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					selectLevelScreen.increasePointer();
				}	
			}
			
			case WIN -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					selectLevelScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					selectLevelScreen.increasePointer();
				}	
			}
			
			case GAME_OVER -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					selectLevelScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					selectLevelScreen.increasePointer();
				}	
			}
			
			default -> {
				
			}
			
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}