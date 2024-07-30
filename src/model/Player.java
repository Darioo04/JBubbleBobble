package model;

import java.awt.Rectangle;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	
	private long score;
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;	
	private boolean isJumping;
	private int speed;
	private String direction;
	private int lives;
	private int speedY; //velocita verticale
	private static final int JUMP_STRENGTH = 15; // Forza del salto
	
	
	private Player() {
		super(50, 50, "Player");
		setDefaultValues();
		this.hitboxWidth = GameConstants.TILE_SIZE;
		this.hitboxHeight = GameConstants.TILE_SIZE;
		setHitbox(new Rectangle(x, y, hitboxWidth, hitboxHeight));
	}
	
	public static Player getInstance() {
		if (instance==null) instance= new Player();
		return instance;
	}
	
	public void setDefaultValues() {
		this.speed = 5;
		this.lives = 3;
		this.score = 0;
		setDead(false);
		this.isJumping = false;
		this.setPath("/sprites/BubAndBob1/Bub-0.png");
		this.direction = "right";
	}
	
	public void setDirection() {
		if(isLeftPressed) direction = "left";
		else if(isRightPressed) direction = "right";
	}
	
	public void updateHitbox() {
		setHitboxX(x);
		setHitboxY(y);
	}
	
	public long getScore() {
		return score;
	}
	
	public void addScore(long score) {
		this.score+=score;
		update();
	}
	
	public void resetScore() {
		this.score=0;
	}
	
	public void shot() {
		
	}
	
	public void jump() {
		if(!isJumping) {
			this.speedY = -JUMP_STRENGTH;
            this.isJumping = true;
		}
	}
	
	@Override
	public void update() {
		setDirection();
		if (isJumping) {
            speedY += GRAVITY; // Aumenta la velocità verso il basso a causa della gravità
            y += speedY; // Aggiorna la posizione verticale

            // Controlla se il giocatore ha toccato il suolo (y = 0 è considerato il suolo)
            if (y >= 0) {
                y = 0;
                speedY = 0;
                isJumping = false; // Termina il salto
            }
        }
		
		switch (direction){
		case "left" -> {
			if (isLeftPressed) {
				x -= speed;
                if (x < 0) x = 0; // Non può essere negativo
			}
		}
		
		case "right" -> {
				if (isRightPressed) {
                x += speed;
                if (x + hitboxWidth > GameConstants.SCREEN_WIDTH) x = GameConstants.SCREEN_WIDTH - hitboxWidth; // Non può essere superiore alla larghezza dello schermo
            }
		}
		
		default ->
		throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		updateHitbox();
        setChanged();
        notifyObservers();
	}
	
	public void setLeftPressed(boolean isLeftPressed) {
		this.isLeftPressed = isLeftPressed;
	}
	
	public void setRightPressed(boolean isRightPressed) {
        this.isRightPressed = isRightPressed;
    }
}
