package simulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PhysicsSimulator {
	private List<Body> bodies;
	private List<SimulatorObserver> observers;
	private double currentTime;
    private double deltaTime;
    private GravityLaws gravityLaw;
     
     public static final int DIMENSION = 2;
     
     public PhysicsSimulator(double deltaTime, GravityLaws law) {
    	 if(deltaTime <= 0.0)throw new IllegalArgumentException(": cycle time must be positive");
    	 if(law == null) throw new IllegalArgumentException(": a concrete gravity law must be specified");
    	 
    	 this.bodies = new ArrayList<Body>();
    	 this.observers = new ArrayList<SimulatorObserver>();
    	 this.currentTime = 0.0;
    	 this.deltaTime= deltaTime;
    	 this.gravityLaw= law;
     }
     
     public void setDeltaTime(double deltaTime) {
    	 if(deltaTime <= 0.0)throw new IllegalArgumentException(": delta time must be positive");
    	 this.deltaTime = deltaTime;
    	 
    	 ListIterator<SimulatorObserver> it = observers.listIterator();
    	 while(it.hasNext()) {
    		 it.next().onDeltaTimeChanged(deltaTime);
    	 }
     }
     
     public void setGravityLaw(GravityLaws gravityLaw) {
    	 if(gravityLaw == null) throw new IllegalArgumentException(": a concrete gravity law must be specified");
    	 this.gravityLaw = gravityLaw;
    	 
    	 ListIterator<SimulatorObserver> it = observers.listIterator();
    	 while(it.hasNext()) {
    		 it.next().onGravityLawChanged(gravityLaw.toString());
    	 }
     }
     
     public void reset() {
    	 bodies.clear();
    	 currentTime = 0.0;
    	 
    	 ListIterator<SimulatorObserver> it = observers.listIterator();
    	 while(it.hasNext()) {
    		 it.next().onReset(bodies, currentTime, deltaTime, gravityLaw.toString());
    	 }
     }
     
     public void advance() {
    	 gravityLaw.apply(bodies);
    	 
    	 ListIterator<Body> it = bodies.listIterator();
    	 while(it.hasNext()) it.next().move(deltaTime);
    	 currentTime += deltaTime;
    	 
    	 ListIterator<SimulatorObserver> it2 = observers.listIterator();
    	 while(it2.hasNext()) {
    		 it2.next().onAdvance(bodies, currentTime);
    	 }
     }
     
     public void addBody(Body b) {
    	 ListIterator<Body> it = bodies.listIterator();
    	 while(it.hasNext()) {
    		 if(it.next().equals(b)) throw new IllegalArgumentException("this body is already on the list: "+b.getId());
    	 }
    	 
    	 bodies.add(b);
    	 
    	 ListIterator<SimulatorObserver> it2 = observers.listIterator();
    	 while(it2.hasNext()) {
    		 it2.next().onBodyAdded(bodies, b);
    	 }
     }
     
     public void addObserver(SimulatorObserver obs) {
    	 ListIterator<SimulatorObserver> it = observers.listIterator();
    	 while(it.hasNext()) {
    		 if(it.next().equals(obs)) throw new IllegalArgumentException("this observer is already on the list");
    	 }
    	 //a lo mejor sobra, en que circunstancias son dos observer iguales
    	 
    	 observers.add(obs);
    	 obs.onRegister(bodies, currentTime, deltaTime, gravityLaw.toString());
     }
     
     
     
     public String toString() {
    	 StringBuilder sb=  new StringBuilder();
    	 sb.append("{ \"time\": ");
    	 sb.append(currentTime);
    	 sb.append(", \"bodies\": [");
    	 
    	 ListIterator<Body> it = bodies.listIterator();
    	 sb.append(it.next().toString());
    	 while(it.hasNext()) {
    		 sb.append(" , ");
    		 sb.append(it.next().toString());
    	 }
    	 
    	 return sb.toString();
     }
     
     
     
     
}
