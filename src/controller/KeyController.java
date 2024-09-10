package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameModel;
import model.GameOverScreen;
import model.GameState;
import model.LastLevelWinScreen;
import model.MenuScreen;
import model.PauseScreen;
import model.Player;
import model.SelectLevelScreen;
import model.WinScreen;
import view.GamePanel;
import view.LevelEditorView;
import view.ProfileView;

public class KeyController implements KeyListener {
	
	private static KeyController instance;
	
	private Player player;
	private GameModel gameModel;
	private GameController gameController;
	private MenuScreen menuScreen;
	private SelectLevelScreen selectLevelScreen;
	private ProfileView profileView;
	private PauseScreen pauseScreen;
	private WinScreen winScreen;
	private GameOverScreen gameOverScreen;
	private AudioManager audioManager;
	private LevelEditorView levelEditorView;
	
	public static KeyController getInstance() {
		if (instance == null) instance = new KeyController();
        return instance;
    }
	
    private KeyController() {
    	gameModel = GameModel.getInstance();
    	menuScreen = MenuScreen.getInstance();
    	selectLevelScreen = SelectLevelScreen.getInstance();
    	gameOverScreen = GameOverScreen.getInstance();
    	player = Player.getInstance();
    	pauseScreen = PauseScreen.getInstance();
    	winScreen = WinScreen.getInstance();
    	audioManager = AudioManager.getInstance();
    	levelEditorView = LevelEditorView.getInstance();
    	profileView = ProfileView.getInstance();
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
					audioManager.play("scroll");
					menuScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					menuScreen.increasePointer();
					audioManager.play("scroll");
				}
				if (key == KeyEvent.VK_ENTER) {
					audioManager.play("click");
					switch (menuScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), selectLevelScreen.getStateScreenView());
							gameController.setGameState(GameState.SELECT_LEVEL);
							selectLevelScreen.update();
						}
						case 1 -> {
							gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), ProfileView.getInstance());
							gameController.setGameState(GameState.SELECT_PROFILE);
							profileView.repaint();
						}
						case 2 -> {
							gameController.changeDisplayedScreen(menuScreen.getStateScreenView(), LevelEditorView.getInstance());
							gameController.setGameState(GameState.LEVEL_EDITOR);
						}
						default -> {
							
						}
					}
				}
			}
			
			case SELECT_LEVEL -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					audioManager.play("scroll");
					selectLevelScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					audioManager.play("scroll");
					selectLevelScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					audioManager.play("click");
					gameController.changeDisplayedScreen(selectLevelScreen.getStateScreenView(), GamePanel.getInstance());
					gameController.setGameState(GameState.GAME);
					gameController.setLevel(selectLevelScreen.getPointer()+1);
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
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					player.setIsJumping(true);
					audioManager.play("jump");
				}
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					player.setLeftPressed(true);
				}
				if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					player.setRightPressed(true);
				}
				if (key == KeyEvent.VK_SPACE) {
				    if (player.canShoot()) {
				        player.setSpacePressed(true);
				        gameController.bubbleShooted();
				        audioManager.play("shooting");
				        player.setCanShoot(false); // Impedisce di sparare di nuovo finché il tasto non viene rilasciato
				    }
				}
		
				if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_Q) {
					gameController.changeDisplayedScreen(GamePanel.getInstance(), pauseScreen.getStateScreenView());
					gameController.setGameState(GameState.PAUSE);
					pauseScreen.update();
				}
			}
			
			case PAUSE -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					audioManager.play("scroll");
					pauseScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					audioManager.play("scroll");
					pauseScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					audioManager.play("click");
					switch (pauseScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(pauseScreen.getStateScreenView(),GamePanel.getInstance());
							gameController.setGameState(GameState.GAME);
						}
						case 1 -> {
							gameController.changeDisplayedScreen(pauseScreen.getStateScreenView(), menuScreen.getStateScreenView());
							gameController.setGameState(GameState.MENU);
							selectLevelScreen.setPointer(0);
							gameController.clearLevel();
							menuScreen.update();
							audioManager.resumeBackgroundMusic();
						}
						default -> {
							
						}
					}
				}
			}
			
			case SELECT_PROFILE -> {
				if (key == KeyEvent.VK_ESCAPE) {
					gameController.changeDisplayedScreen(ProfileView.getInstance(),menuScreen.getStateScreenView());
					gameController.setGameState(GameState.MENU);
					menuScreen.update();
				}
			}
			
			case WIN -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					audioManager.play("scroll");
					winScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					audioManager.play("scroll");
					winScreen.increasePointer();
				}
				if (key == KeyEvent.VK_ENTER) {
					audioManager.play("click");
					switch (winScreen.getPointer()) {
						case 0 -> {
							gameController.clearLevel();
							gameModel.increaseGamesPlayed();
							gameModel.increaseGamesWon();
							gameController.changeDisplayedScreen(winScreen.getStateScreenView(),GamePanel.getInstance());
							gameController.setGameState(GameState.GAME);
							selectLevelScreen.increasePointer();
							gameController.setLevel(selectLevelScreen.getPointer()+1);
							gameController.startLevel();
							audioManager.stopLevelMusic();
							audioManager.playLevelMusic("level");
						}
						case 1 -> {
							gameController.changeDisplayedScreen(winScreen.getStateScreenView(),menuScreen.getStateScreenView());
							gameController.setGameState(GameState.MENU);
							gameController.clearLevel();
							gameModel.increaseGamesPlayed();
							gameModel.increaseGamesWon();
							menuScreen.update();
							audioManager.resumeBackgroundMusic();
						}
						default -> {
							
						}
					}
				}
			}
			
			case LAST_WIN -> {
				if (key == KeyEvent.VK_ENTER) {
					gameController.changeDisplayedScreen(LastLevelWinScreen.getInstance().getStateScreenView(), ProfileView.getInstance());
					gameController.setGameState(GameState.SELECT_PROFILE);
					gameController.clearLevel();
					gameModel.increaseGamesPlayed();
					gameModel.increaseGamesWon();
					audioManager.resumeBackgroundMusic();
				}
			}
			
			case GAME_OVER -> {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					audioManager.play("scroll");
					gameOverScreen.decreasePointer();
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					audioManager.play("scroll");
					gameOverScreen.increasePointer();
				}	
				if (key == KeyEvent.VK_ENTER) {
					audioManager.play("click");
					switch (gameOverScreen.getPointer()) {
						case 0 -> {
							gameController.changeDisplayedScreen(gameOverScreen.getStateScreenView(),GamePanel.getInstance());
							gameController.setGameState(GameState.GAME);
							gameController.clearLevel();
							gameModel.increaseGamesPlayed();
							gameModel.increaseGamesLost();
							gameController.setScore(0);
							gameController.startLevel();
						}
						case 1 -> {
							gameController.changeDisplayedScreen(gameOverScreen.getStateScreenView(),menuScreen.getStateScreenView());
							gameController.setGameState(GameState.MENU);
							gameController.clearLevel();
							gameModel.increaseGamesPlayed();
							gameModel.increaseGamesLost();
							gameController.setScore(0);
							menuScreen.update();
							audioManager.resumeBackgroundMusic();
						}
					}
				}
			}
			
			case LEVEL_EDITOR -> {
				if (key == KeyEvent.VK_ESCAPE) {
                    gameController.changeDisplayedScreen(LevelEditorView.getInstance(), menuScreen.getStateScreenView());
                    levelEditorView.resetEditor();
                    gameController.setGameState(GameState.MENU);
                    menuScreen.update();
                }
				if (key == KeyEvent.VK_0) {
					levelEditorView.setTile(0);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
				}
				if (key == KeyEvent.VK_1) {
					levelEditorView.setTile(1);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
				}
				if (key == KeyEvent.VK_2) {
					levelEditorView.setTile(2);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_3) {
					levelEditorView.setTile(3);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_4) {
					levelEditorView.setTile(4);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_5) {
					levelEditorView.setTile(5);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_6) {
					levelEditorView.setTile(6);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_7) {
					levelEditorView.setTile(7);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_8) {
					levelEditorView.setTile(8);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_9) {
					levelEditorView.setTile(9);
					levelEditorView.setTileSelected(true);
					levelEditorView.repaint();
					levelEditorView.setAddTile(true);
                }
				if (key == KeyEvent.VK_W) {
                    levelEditorView.setAddTile(true);
                    levelEditorView.setRemoveTile(false);
                    levelEditorView.repaint();
                }
				if (key == KeyEvent.VK_S) {
                    levelEditorView.setAddTile(false);
                    levelEditorView.setRemoveTile(false);
                    levelEditorView.repaint();
                }
				if (key == KeyEvent.VK_BACK_SPACE) {
					levelEditorView.setAddTile(false);
					levelEditorView.setRemoveTile(true);
					levelEditorView.repaint();
				}
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected state: " + gameController.getGameState());
			}
			
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if (gameController.getGameState() == GameState.GAME) {
    		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				player.setIsJumping(false);
			}
    		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				player.setLeftPressed(false);
			}
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				player.setRightPressed(false);
			}
			if (key == KeyEvent.VK_SPACE) {
				player.setSpacePressed(false);
			}
			if (key == KeyEvent.VK_SPACE) {
			    player.setSpacePressed(false);
			    player.setCanShoot(true);
			
    	}
    	
    }

}
}