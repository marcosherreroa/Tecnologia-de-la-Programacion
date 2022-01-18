package simulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PhysicsSimulator {
	private List<Body> bodies;
	private double currentTime;
	
     private final double deltaTime;
     private final GravityLaws law;
     
     public static final int DIMENSION = 2;
     
     public PhysicsSimulator(double deltaTime, GravityLaws law) {
    	 if(deltaTime <= 0.0)throw new IllegalArgumentException(": cycle time must be positive");
    	 if(law == null) throw new IllegalArgumentException(": a concrete gravity law must be specified");
    	 
    	 this.bodies = new ArrayList<Body>();
    	 this.currentTime = 0.0;
    	 this.deltaTime= deltaTime;
    	 this.law= law;
     }
     
     public void advance() {
    	 law.apply(bodies);
    	 
    	 ListIterator<Body> it = bodies.listIterator();
    	 while(it.hasNext()) it.next().move(deltaTime);
    	 currentTime += deltaTime;
     }
     
     public void addBody(Body b) {
    	 ListIterator<Body> it = bodies.listIterator();
    	 String id = b.getId();
    	 while(it.hasNext()) {
    		 if(it.next().getId().equals(id)) throw new IllegalArgumentException("there is already a body with this id: "+id);
    	 }
    	 
    	 bodies.add(b);
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
