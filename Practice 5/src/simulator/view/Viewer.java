package simulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {
	private int centerX;
	private int centerY;
	private double scale;
	private List<Body> bodies;
	private boolean showHelp;
	
	public static final int BODYRADIUS = 5;
	public static final int EPSILON = 5;
	
	Viewer(Controller controller) {
	initGUI();
	controller.addObserver(this);
	}
	private void initGUI() {
	this.setBorder(BorderFactory.createTitledBorder("Viewer"));
	this.setBackground(Color.WHITE);
	bodies = new ArrayList<>();
	scale = 1.0;
	showHelp = true;
	addKeyListener(new KeyListener() {
		
	public void keyPressed(KeyEvent e) {
	switch (e.getKeyChar()) {
	case '-':{
	scale = scale * 1.1;
	break;
	}
	case '+':{
	scale = Math.max(1000.0, scale / 1.1);
	break;
	}
	case '=':{
	autoScale();
	break;
	}
	case 'h':{
	showHelp = !showHelp;
	break;
	}
	default:
	}
	repaint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
	});
	
	addMouseListener(new MouseListener() {

	public void mouseEntered(MouseEvent e) {
	requestFocus();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	});
	}
	
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D gr = (Graphics2D) g;
	gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	// use ’gr’ to draw not ’g’
	// calculate the center
	centerX = getWidth() / 2;
	centerY = getHeight() / 2;
	String helpMessage = "h: toggle help, +: zoom-in, -: zoom-out, =: fit"+System.lineSeparator()+"Scaling ratio: "+scale;
	
	gr.drawLine(centerX, centerY, centerX, centerY+EPSILON);
	gr.drawLine(centerX, centerY, centerX+EPSILON, centerY);
	gr.drawLine(centerX, centerY, centerX, centerY-EPSILON);
	gr.drawLine(centerX, centerY, centerX-EPSILON, centerY);
	
	if(showHelp) {
		gr.setColor(Color.RED);
		gr.drawString(helpMessage, 5, 25);
	}
	
	// TODO draw bodies
    ListIterator<Body> it = bodies.listIterator(); Body b; int bodyCenterX, bodyCenterY;
     while(it.hasNext()) {
     	b = it.next();
     	bodyCenterX = centerX+ (int)( b.getPosition().coordinate(0)/scale);
     	bodyCenterY = centerY+ (int)( b.getPosition().coordinate(1)/scale);
     	gr.setColor(Color.BLUE);
     	//gr.drawOval(bodyCenterX, bodyCenterY, BODYRADIUS, BODYRADIUS);
     	gr.fillOval(bodyCenterX, bodyCenterY, BODYRADIUS, BODYRADIUS);
     	gr.setColor(Color.BLACK);
     	gr.drawString(b.getId(), bodyCenterX, bodyCenterY);
     }
     
     
     
	}
	
	// other private/protected methods
	// ...
	private void autoScale() {
	double max = 1.0;
	for (Body b : bodies) {
	Vector p = b.getPosition();
	for (int i = 0; i < p.dim(); i++)
	max = Math.max(max,
	Math.abs(b.getPosition().coordinate(i)));
	}
	double size = Math.max(1.0, Math.min((double) getWidth(),
	(double) getHeight()));
	scale = max > size ? 4.0 * max / size : 1.0;
	}
	// SimulatorObserver methods
	// ...
	
	public void onRegister(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
	   this.bodies = bodies;
	   autoScale();
	   repaint();
	}
	
	public void onReset(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		this.bodies = bodies;
		autoScale();
		repaint();
	}
	
	public void onBodyAdded(List<Body> bodies, Body b) {
		this.bodies = bodies;
		autoScale();
		repaint();
	}
	
	public void onAdvance(List<Body> bodies, double currentTime) {
		repaint();
	}
	
	public void onDeltaTimeChanged(double deltaTime) {
	}
	
	public void onGravityLawChanged(String gLawsDesc) {
	}
}


