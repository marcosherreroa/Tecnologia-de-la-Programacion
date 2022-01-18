package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame{
	Controller controller;
	
	public MainWindow(Controller ctrl) {
	super("Physics Simulator");
	controller = ctrl;
	initGUI();
	}
	
	private void initGUI() {
	JPanel mainPanel = new JPanel(new BorderLayout());
	setContentPane(mainPanel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	ControlPanel controlPanel = new ControlPanel(controller);
	mainPanel.add(controlPanel, BorderLayout.PAGE_START);
	
	StatusBar statusBar = new StatusBar(controller);
	statusBar.setPreferredSize(new Dimension(2000, 40));
	statusBar.setMinimumSize(new Dimension(2000, 40));
	mainPanel.add(statusBar, BorderLayout.PAGE_END);
	
	JPanel centerPanel = new JPanel(); 
	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	mainPanel.add(centerPanel, BorderLayout.CENTER);
	
	BodiesTable bodiesTable = new BodiesTable(controller);
	bodiesTable.setPreferredSize(new Dimension(100, 400));
	centerPanel.add(bodiesTable);
	Viewer viewer = new Viewer(controller);
	viewer.setPreferredSize(new Dimension(1000,400));
	centerPanel.add(viewer);
	centerPanel.setVisible(true);
	
	mainPanel.setVisible(true);
	this.pack();
	this.setVisible(true);
	this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
	
}
