package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JL;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver{
	private JL currentTime; // for current time
	private JL currentLaw; // for gravity laws
	private JL numOfBodies; // for number of bodies
	
	public static final JL CURRENTTIMEL = new JL("Time: ");
	public static final JL CURRENTLAWL = new JL("Laws: ");
	public static final JL NUMOFBODIESL = new JL("Bodies: ");
	public static final JL LAWSL = new JL("Laws: ");
	
	StatusBar(Controller controller) {
	currentTime = new JL();
	currentLaw = new JL();
	numOfBodies = new JL();
	initGUI();
	controller.addObserver(this);
	}
	
	private void initGUI() {
	this.setLayout( new FlowLayout( FlowLayout.LEFT ));
	this.setBorder( BorderFactory.createBevelBorder( 1 ));
    
	CURRENTTIMEL.setLFor(currentTime);
	this.add(CURRENTTIMEL);
	

	CURRENTTIMEL.setLFor(currentTime);
	this.add(CURRENTTIMEL);
	currentTime.setPreferredSize(new Dimension(100, 20));
	this.add(currentTime);
	
	NUMOFBODIESL.setLFor(currentTime);
	this.add(NUMOFBODIESL);
	numOfBodies.setPreferredSize(new Dimension(200, 20));
	this.add(numOfBodies);
	
	CURRENTTIMEL.setLFor(currentLaw);
	currentLaw.setPreferredSize(new Dimension(1000, 20));
	this.add(LAWSL);
	this.add(currentLaw);;
	
	}
	
	public void onRegister(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		this.currentTime.setText(Double.toString(currentTime));
		this.currentLaw.setText(gLawsDesc);
		this.numOfBodies.setText(Integer.toString(bodies.size()));
	}
	
	public void onReset(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		this.currentTime.setText(Double.toString(currentTime));
		this.currentLaw.setText(gLawsDesc);
		this.numOfBodies.setText(Integer.toString(bodies.size()));
	}

	public void onBodyAdded(List<Body> bodies, Body b) {
		this.numOfBodies.setText(Integer.toString(bodies.size()));
	}

	public void onAdvance(List<Body> bodies, double currentTime) {
		this.currentTime.setText(Double.toString(currentTime));
	}

	public void onDeltaTimeChanged(double deltaTime) {
	}
	
	public void onGravityLawChanged(String gLawsDesc) {
		this.currentLaw.setText(gLawsDesc);
	}
}
