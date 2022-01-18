package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	private List<Body> bodies; 
	
	BodiesTableModel(Controller controller) {
	bodies = new ArrayList<>();
	controller.addObserver(this);
	}
	
	public int getRowCount() {
	   return bodies.size();
	}
	
	public int getColumnCount() {
	  return Body.properties.length;
	}
	
	public String getColumnName(int column) {
	  return Body.properties[column];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex >= bodies.size()) throw new IllegalArgumentException("There are only "+bodies.size()+" bodies");
		Body b = bodies.get(rowIndex);
		
		switch(columnIndex) { //habra una forma mejor?
		case 0:return b.getId();
		case 1:return b.getMass();
		case 2:return b.getPosition();
		case 3:return b.getVelocity();
		case 4:return b.getAcceleration();
		default: throw new IllegalArgumentException("Bodies only have "+Body.properties.length+" properties");
		}
	  
	}

	@Override
	public void onRegister(List<Body> bodies, double curentTime, double deltaTime, String gLawsDesc) {
		this.bodies = bodies;
		fireTableStructureChanged();
	}

	@Override
	public void onReset(List<Body> bodies, double currentTime, double deltaTime, String gLawsDesc) {
		this.bodies = bodies;
		fireTableStructureChanged();
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		this.bodies = bodies;
		fireTableStructureChanged();
	}

	@Override
	public void onAdvance(List<Body> bodies, double currentTime) {
		//this.bodies = bodies;
		fireTableStructureChanged();
	}

	@Override
	public void onDeltaTimeChanged(double deltaTime) {
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
	}
	
}
