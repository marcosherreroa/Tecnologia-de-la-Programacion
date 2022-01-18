package simulator.model;

import java.util.List;

public class NoGravity implements GravityLaws{
	public static final String DESC = "No gravity";
	public void apply(List<Body> bodies) {};
	
	public String toString() {
		return DESC;
	}
}
