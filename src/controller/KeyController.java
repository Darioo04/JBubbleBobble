package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameOverScreen;
import model.GameState;
import model.MenuScreen;
import model.PauseScreen;
import model.Player;
import model.SelectLevelScreen;
import model.SelectProfileScreen;
import model.WinScreen;
import view.GamePanel;
import view.MenuScreenView;

public class KeyController implements KeyListener {
	
	private static KeyController instance;
	
	private Player player;
	private GameController gameController;
	private MenuScreen menuScreen;
	private SelectLevelScreen selectLevelScreen;
	private PauseScreen pauseScreen;
	private WinScreen winScreen;
	private GameOverScreen gameOverScreen;
//	private SelectProfileScreen selectProfileScreen = SelectProfileScreen.getInstance();
	
	public static KeyController getInstance() {
		if (instance == null) instance = new KeyController();
        return instance;
    }
	
    private KeyController() {
    	menuScreen = MenuScreen.getInstance();
    	selectLevelScreen = SelectLevelScreen.getInstance();
    	player = Player.getInstance();
    	pauseScreen = PauseScreen.getInstance();
    	winScreen = WinScreen.getInstance();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	gameController = GameController.getInstance();   //questo sta qua perche nel suo costruttore ha un getinstance di del keycontroller, se lo mettessi nel costruttore si ha una chiamata circolare tra le 2 classi
    	
        int key = e.getKeyCode();
        
        switch (gameController.getGameState()) {
			case MENU -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					menuScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					menuScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(key);
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
					gameController.changeDisplayedScreen(selectLevelScreen.getStateScreenView(), GamePanel.getInstance());
					gameController.setGameState(GameState.GAME);
					gameController.startLevel();
				}
				if (key == KeyEvent.VK_ESCAPE) {
					gameController.changeDisplayedScreen(selectLevelScreen.getStateScreenView(), menuScreen.getStateScreenView());
					gameController.setGameState(GameState.MENU);
					menuScreen.update();
				}
			}
			
			case LOGIN -> {
				
			}
				
			case GAME -> {
				
				//player controller
//				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
//					player.move(0,-13);
//				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					player.move(0,13);
				}
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					player.move(-13,0);
				}
				if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					player.move(13,0);
				}
				if (key == KeyEvent.VK_SPACE) {
					player.shot();
				}
				if (key == KeyEvent.VK_W) {
                    player.jump();
                }
				if (key == KeyEvent.VK_ESCAPE) {
					gameController.changeDisplayedScreen(GamePanel.getInstance(), pauseScreen.getStateScreenView());
					gameController.setGameState(GameState.PAUSE);
					pauseScreen.update();
				}
			}
			
			case PAUSE -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					pauseScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					pauseScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					switch (pauseScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(pauseScreen.getStateScreenView(),GamePanel.getInstance());
							gameController.setGameState(GameState.GAME);
//							pauseScreen.update();
						}
						case 1 -> {
							gameController.changeDisplayedScreen(pauseScreen.getStateScreenView(),menuScreen.getStateScreenView());
							gameController.setGameState(GameState.MENU);
							menuScreen.update();
						}
						default -> {
							
						}
					}
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
					winScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					winScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					switch (winScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(winScreen.getStateScreenView(),GamePanel.getInstance());
							gameController.setGameState(GameState.GAME);
							selectLevelScreen.increasePointer();
							gameController.startLevel();
						}
						case 1 -> {
							gameController.changeDisplayedScreen(winScreen.getStateScreenView(),menuScreen.getStateScreenView());
							gameController.setGameState(GameState.MENU);
							menuScreen.update();
						}
						default -> {
							
						}
					}
				}
			}
			
			case GAME_OVER -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					gameOverScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					gameOverScreen.increasePointer();
				}	
				if (key == KeyEvent.VK_ENTER) {
					switch (gameOverScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(gameOverScreen.getStateScreenView(),GamePanel.getInstance());
							gameController.setGameState(GameState.GAME);
							gameController.startLevel();
						}
						case 1 -> {
							gameController.changeDisplayedScreen(gameOverScreen.getStateScreenView(),menuScreen.getStateScreenView());
							gameController.setGameState(GameState.MENU);
							menuScreen.update();
						}
					}
				}
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected state: " + gameController.getGameState());
			}
			
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    }

}