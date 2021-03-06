package simulator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JL;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{
	private Controller controller;
	private volatile Thread thread;
	private JSpinner stepSelector;
	private JTextField deltaTimeSelector;
	private JSpinner delaySelector;
	
	private Set<JButton> toDisable;
	
	public static final String STEPSELECTORL = "Steps:";
	public static final int STEPSELECTORMIN = 500;
	public static final int STEPSELECTORMAX = 10000;
	public static final int STEPSIZE = 500;
	
	public static final String DELTATIMESELECTORL = "Delta_Time:";
	
	public static final String DELAYSELECTORL = "Delay: ";
	public static final int DELAYSELECTORMIN = 0;
	public static final int DELAYSELECTORMAX = 1000;
	public static final int DELAYSTEP = 1;
	
	ControlPanel(Controller controller){
		this.controller = controller;
		this.toDisable = new HashSet<JButton>();
		initGUI();
		controller.addObserver(this);
	}
	
	private JButton initLoadFile() {
		JButton loadFile = new JButton();
		loadFile.setToolTipText("Load a file");

		loadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setCurrentDirectory(new File("resources"));
				fc.showDialog(new JFrame(), "Load");
				File file = fc.getSelectedFile();
				FileInputStream input;
				try {
					input = new FileInputStream(file);
					controller.reset();
					controller.loadBodies(input);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "The selected file cannot be read",
							"FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				}
		}});
		
		loadFile.setIcon(new ImageIcon("resources/icons/open.png"));
		
		toDisable.add(loadFile);
		return loadFile;
	}
	
	private JButton initSetGravity() {
		JButton setGravity = new JButton();
		setGravity.setToolTipText("Set the gravity law");
		
		setGravity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<JSONObject> list = controller.getGravityLawsFactory().getInfo();
				String[] options = new String[list.size()];
				ListIterator<JSONObject> it = list.listIterator();
				int i = 0; JSONObject jo;
				while(it.hasNext()) {
					jo = it.next();
					options[i] = jo.getString("desc")+"("+jo.getString("type")+")";
					++i;
				}
	            
				Object selected = JOptionPane.showInputDialog(new JFrame(), "Select gravity laws to be used: ","Gravity Laws Selector",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]); 
				
				boolean found = false;
				for(int j = 0; !found && j< list.size(); ++j) {
					if(selected.equals(options[j])) {
						controller.setGravityLaws(list.get(j));
						found = true;
					}
				}
				
			}
		});
		
		setGravity.setIcon(new ImageIcon("resources/icons/physics.png"));
		
		toDisable.add(setGravity);
		return setGravity;
	}
	
	private JButton initRun() {
		JButton run = new JButton();
		run.setToolTipText("Run simulation");
		
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				long delay = Long.parseLong(delaySelector.getValue().toString());
				double dt = Double.parseDouble(deltaTimeSelector.getText());
				int steps = Integer.parseInt(stepSelector.getValue().toString());
				
				Iterator<JButton> it = toDisable.iterator();
				JButton b;
				while(it.hasNext()) {
					b = it.next();
					b.setEnabled(false);
				}
				
				controller.setDeltaTime(dt);
				thread = new Thread(new Runnable(){
					public void run() {
						run_sim(steps, delay);
						Iterator<JButton> it = toDisable.iterator();
						JButton b;
      					while(it.hasNext()) {
							b = it.next();
    						b.setEnabled(true);
						}
      					thread = null;
					}	
				});
				thread.start();
				
			    }catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Delay , Delta time and Steps must be numbers",
						"NumberFormatException", JOptionPane.ERROR_MESSAGE);
			     } catch(IllegalArgumentException ex){
				JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),
						"IllegalArgumentException", JOptionPane.ERROR_MESSAGE);
			     }
			}
		});
		
		run.setIcon(new ImageIcon("resources/icons/run.png"));
		
		toDisable.add(run);
		return run;
	}
	
	private JButton initStop() {
		JButton stop = new JButton();
		stop.setToolTipText("Stop simulation");
		
		stop.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			   if(thread != null)thread.interrupt();
		    }
		});
		
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));
		return stop;
	}
	
	private JButton initExit() {
		JButton exit = new JButton();
		exit.setToolTipText("Exits the program");
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to exit?", null, JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION) System.exit(0);
			}
		});
		
		exit.setIcon(new ImageIcon("resources/icons/exit.png"));
		exit.setAlignmentX(RIGHT_ALIGNMENT);
		toDisable.add(exit);
		return exit;
	}
	
	private void initGUI() {
		JToolBar bar = new JToolBar();
		bar.setPreferredSize(new Dimension(1000, 50));
		
		bar.add(initLoadFile());
		bar.addSeparator();
		bar.add(initSetGravity());
		bar.addSeparator();
		bar.add(initRun());
		bar.add(initStop());
		bar.addSeparator();
		
		JL lDelaySelector = new JL(DELAYSELECTORL);
		lDelaySelector.setLFor(delaySelector);
		delaySelector = new JSpinner(new SpinnerNumberModel(1, DELAYSELECTORMIN, DELAYSELECTORMAX, DELAYSTEP));
		delaySelector.setMaximumSize(new Dimension(60, 40));
		delaySelector.setPreferredSize(new Dimension(60, 40));
		bar.add(lDelaySelector);
		bar.addSeparator();
		bar.add(delaySelector);
		bar.addSeparator();
		
		JL lStepSelector = new JL(STEPSELECTORL);
		lStepSelector.setLFor(stepSelector);
		stepSelector = new JSpinner(new SpinnerNumberModel(500, STEPSELECTORMIN, STEPSELECTORMAX, STEPSIZE));
		stepSelector.setMaximumSize(new Dimension(60, 40));
		stepSelector.setPreferredSize(new Dimension(60, 40));
		bar.add(lStepSelector);
		bar.addSeparator();
		bar.add(stepSelector);
		bar.addSeparator();
		
		JL lDeltaTimeSelector = new JL(DELTATIMESELECTORL);
		deltaTimeSelector = new JTextField(); 
		lDeltaTimeSelector.setLFor(deltaTimeSelector);
		deltaTimeSelector.setMaximumSize(new Dimension(1, 50));
		deltaTimeSelector.setColumns(5);
		bar.add(lDeltaTimeSelector);
		bar.addSeparator();
		bar.add(deltaTimeSelector);
		
		bar.add(Box.createHorizontalGlue());
		bar.add(initExit());
		bar.setPreferredSize(new Dimension(1530, 50));
		
		this.add(bar);
	}
	
	private void run_sim(int n, long delay) {
		while ( n>0 && !Thread.interrupted() ) {
		try {
		controller.run(1);
		} catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());			
           Thread.currentThread().interrupt();
		}
		
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		--n;
		} 
	}

	
	public void onRegister(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) { 
		SwingUtilities.invokeLater(new Runnable() {
		   public void run(){
		    deltaTimeSelector.setText(Double.toString(deltaTime));
		   }
		});
	}

	public void onReset(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
		     public void run() {
		        deltaTimeSelector.setText(Double.toString(deltaTime));
		     }
		});
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
	}

	@Override
	public void onAdvance(List<Body> bodies, double currentTime) {
	}

	@Override
	public void onDeltaTimeChanged(double deltaTime) {
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      deltaTimeSelector.setText(Double.toString(deltaTime));
		  }
		});
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
	}
	
}
