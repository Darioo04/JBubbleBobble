package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameConstants;

public class WinPanel extends StateScreenView {
	private static WinPanel instance;
	
	public static WinPanel getInstance() {
		if (instance==null) instance = new WinPanel();
		return instance;
	}
	
	public WinPanel() {
		super();
		
	}
}
