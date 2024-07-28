package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameConstants;

public class WinScreenView extends StateScreenView {
	private static WinScreenView instance;
	
	public static WinScreenView getInstance() {
		if (instance==null) instance = new WinScreenView();
		return instance;
	}
	
	private WinScreenView() {
			
	}
}
