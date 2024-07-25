package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SelectLevelView extends StateScreenView implements Observer {
	private static SelectLevelView instance;
	
	public static SelectLevelView getInstance() {
		if (instance==null) instance = new SelectLevelView();
		return instance;
	}
	
	private SelectLevelView() {
		super();
		
	}
	
	@Override
	public void update(Observable o,Object arg) {
		repaint();
	}
	
}
