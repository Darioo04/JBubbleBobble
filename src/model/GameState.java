package model;

public enum GameState {
	LOGIN(1,"login-"),
	SELECT_LEVEL(24,"levels-"),
	GAME(2,""),
    PAUSE(2,"pause-"),
    GAME_OVER(2,"lose-"),
    WIN(2,"win-"),
    MENU(3,"menu-"),
    SELECT_PROFILE(1,"profile-");
    
	private int numScreens;
	private String path;
	
    GameState(int numScreens,String path) {
		this.numScreens=numScreens;
		this.path=path;
	}
    
    public int getNumScreens() {
    	return numScreens;
    }
    
    public String getPath() {
    	return path;
    }
}
