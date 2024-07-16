package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Enemy;

public class EnemyView {
	private Enemy enemy;
	private BufferedImage enemyImage;
	
	public EnemyView(Enemy enemy) {
		this.enemy = enemy;
        try {
        	enemyImage = ImageIO.read(getClass().getResource(/*"/res/sprites/BubAndBob1/*"*/));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
