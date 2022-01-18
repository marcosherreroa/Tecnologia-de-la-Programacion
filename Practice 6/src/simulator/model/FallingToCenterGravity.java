package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class FallingToCenterGravity implements GravityLaws {
	public static final String DESC = "Falling to center gravity";
     private static final double g = 9.81;
     
     public void apply(List<Body> bodies) {
    	 Body bi;
    	 Vector di;
    	 for(int i = 0; i< bodies.size(); ++i) {
    		 bi = bodies.get(i);
    		 di = bi.getPosition().direction();
    		 bi.setAcceleration(di.scale(-g));
    	 }
     }
     
     public String toString() {
 		return DESC;
 	}
}
