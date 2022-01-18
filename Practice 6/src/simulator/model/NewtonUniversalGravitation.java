package simulator.model;

import java.util.List;
import java.util.ListIterator;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws{
	public static final String DESC = "Newton's law of universal gravitation";
	private static final double G = 6.67e-11;
	
	public void apply(List<Body> bodies) {
		Body bi, bj;
		Vector pi,pj,dij, Fij, FT;
		double mi, mj, distance, fij;
		
		ListIterator<Body> iti = bodies.listIterator(), itj;
		while(iti.hasNext()) {
			bi = iti.next();
			mi = bi.getMass();
			if(mi == 0.0) {
				bi.setVelocity(new Vector(PhysicsSimulator.DIMENSION));
				bi.setAcceleration(new Vector(PhysicsSimulator.DIMENSION));
			}
			else {
				FT = new Vector(PhysicsSimulator.DIMENSION);
				pi = bi.getPosition();
				
				itj = bodies.listIterator();
				while(itj.hasNext()) {
					bj = itj.next();
					if(!bj.equals(bi)) {  
						mj = bj.getMass();
						pj = bj.getPosition();
						dij = pj.minus(pi).direction();
						distance = pj.distanceTo(pi);
						
						fij = G*mi*mj /(distance*distance);
						Fij = dij.scale(fij);
						FT= FT.plus(Fij);
					}
				}
				
				bi.setAcceleration(FT.scale(1/mi));
			}
			
		}
	}
	
	public String toString() {
		return DESC;
	}
}
