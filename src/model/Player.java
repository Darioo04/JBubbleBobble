package model;

@SuppressWarnings("deprecation")

public class Player extends Entity {
	
	enum Direction {
		LEFT,RIGHT
	}
	private Direction direction;
	private long score;
	private static Player instance;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isSpacePressed;	
	private boolean isJumping;
	private int speedY; //velocita verticale
	private static final int JUMP_STRENGTH = 15; // Forza del salto
	
	private Player() {
		super(50, 50, "Player");
		this.setPath("/sprites/BubAndBob1/Bub-0.png");
		this.isJumping = false;
		this.direction=Direction.RIGHT;
	}
	
	public static Player getInstance() {
		if (instance==null) instance= new Player();
		return instance;
	}
	
	public long getScore() {
		return score;
	}
	
	public void addScore(long score) {
		this.score+=score;
		setChanged();
		notifyObservers();
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
        setChanged();
        notifyObservers();
	}
	
	public void changeDirection() {
		direction = (direction==Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
	}
}
