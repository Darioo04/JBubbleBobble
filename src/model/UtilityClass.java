package model;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class UtilityClass {
	
	private UtilityClass() {
		throw new UnsupportedOperationException("can't instantiate this class");
	}
	
	
	public static double getScale() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double scaleX = (double) screenSize.getWidth() / GameConstants.ORIGINAL_WIDTH;
        double scaleY = (double) screenSize.getHeight() / GameConstants.ORIGINAL_HEIGHT;
        return Math.min(scaleX, scaleY);	
	}
}
