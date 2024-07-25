package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameState;
import model.MenuScreen;
import model.Player;
import model.SelectLevelScreen;
import view.MenuScreenPanel;

public class KeyController implements KeyListener {
	
	private Player player;
	private GameController gameController;
	private MenuScreen menuScreen = MenuScreen.getInstance();
	private SelectLevelScreen selectLevelScreen = SelectLevelScreen.getInstance();
	
	
    public KeyController(Player player) {
        this.player = player;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.move(-5, 0);
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.move(5, 0);
        }
        if (key == KeyEvent.VK_UP) {
            player.move(0, -5);
        }
        if (key == KeyEvent.VK_DOWN) {
            player.move(0, 5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
