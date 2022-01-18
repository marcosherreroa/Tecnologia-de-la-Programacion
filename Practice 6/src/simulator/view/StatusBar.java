package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver{
	private JLabel currentTime; // for current time
	private JLabel currentLaw; // for gravity laws
	private JLabel numOfBodies; // for number of bodies
	
	public static final JLabel CURRENTTIMELABEL = new JLabel("Time: ");
	public static final JLabel CURRENTLAWLABEL = new JLabel("Laws: ");
	public static final JLabel NUMOFBODIESLABEL = new JLabel("Bodies: ");
	public static final JLabel LAWSLABEL = new JLabel("Laws: ");
	
	StatusBar(Controller controller) {
	currentTime = new JLabel();
	currentLaw = new JLabel();
	numOfBodies = new JLabel();
	initGUI();
	controller.addObserver(this);
	}
	
	private void initGUI() {
	this.setLayout( new FlowLayout( FlowLayout.LEFT ));
	this.setBorder( BorderFactory.createBevelBorder( 1 ));
    
	CURRENTTIMELABEL.setLabelFor(currentTime);
	this.add(CURRENTTIMELABEL);
	

	CURRENTTIMELABEL.setLabelFor(currentTime);
	this.add(CURRENTTIMELABEL);
	currentTime.setPreferredSize(new Dimension(100, 20));
	this.add(currentTime);
	
	NUMOFBODIESLABEL.setLabelFor(currentTime);
	this.add(NUMOFBODIESLABEL);
	numOfBodies.setPreferredSize(new Dimension(200, 20));
	this.add(numOfBodies);
	
	CURRENTTIMELABEL.setLabelFor(currentLaw);
	currentLaw.setPreferredSize(new Dimension(1000, 20));
	this.add(LAWSLABEL);
	this.add(currentLaw);;
	
	}
	
	public void onRegister(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StatusBar.this.currentTime.setText(Double.toString(currentTime));
				StatusBar.this.currentLaw.setText(gLawsDesc);
				StatusBar.this.numOfBodies.setText(Integer.toString(bodies.size()));
			}
		});
		
	}
	
	public void onReset(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StatusBar.this.currentTime.setText(Double.toString(currentTime));
				StatusBar.this.currentLaw.setText(gLawsDesc);
				StatusBar.this.numOfBodies.setText(Integer.toString(bodies.size()));
			}
		});
	}

	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StatusBar.this.numOfBodies.setText(Integer.toString(bodies.size()));
			}
		});
	}

	public void onAdvance(List<Body> bodies, double currentTime) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StatusBar.this.currentTime.setText(Double.toString(currentTime));
			}
		});
	}

	public void onDeltaTimeChanged(double deltaTime) {
	}
	
	public void onGravityLawChanged(String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StatusBar.this.currentLaw.setText(gLawsDesc);
			}
		});
	}
}
