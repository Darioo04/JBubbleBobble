package model;

public enum GameState {
	LOGIN(1,"login-"),
	SELECT_LEVEL(25,"levels-"),
	GAME(1,""),
    PAUSE(2,"pause-"),
    GAME_OVER(2,"lose-"),
    WIN(2,"win-"),
    MENU(3,"menu-"),
    SELECT_PROFILE(1,""/*"profile-"*/),
	LEVEL_EDITOR(1,"level-editor");
    
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
