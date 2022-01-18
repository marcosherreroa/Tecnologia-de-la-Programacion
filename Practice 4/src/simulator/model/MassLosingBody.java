package simulator.model;

import simulator.misc.Vector;

public class MassLosingBody extends Body {
    private double lossFactor;
    private double lossFrequency;
    private double cont;
    
    public static final String type = "mlb";
    
	public MassLosingBody(String id, Vector position, Vector velocity, Vector acceleration, double mass,
			double lossFactor, double lossFrequency) {
		super(id, position, velocity, acceleration, mass);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
		this.cont = 0.0;
	}
	
	void move(double t) {
		super.move(t);
		cont += t;
		if(cont >= lossFrequency) {
			mass*= (1-lossFactor);
			cont = 0.0;
		}
		
	}
     
}
