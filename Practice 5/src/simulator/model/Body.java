package simulator.model;

import simulator.misc.Vector;

public class Body {
	private String id;  
	private Vector position;
	private Vector velocity;
	private Vector acceleration;
	private double mass;
	
	public static final String type = "basic";
	public static final String[] properties = {"id", "mass", "position", "velocity", "acceleration"};
	
	public Body(String id, Vector position, Vector velocity, Vector acceleration, double mass) {
		this.id = id;
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.mass = mass;
	}

	public String getId() {
		return id;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	void setPosition(Vector position) {
		this.position = position;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public Vector getAcceleration() {
		return acceleration;
	}
	
	void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
	
	public double getMass() { //dudas sobre la visibilidad
		return mass;
	}
	
	void setMass(double mass) {
		this.mass = mass;
	}
	
	void move(double t) {
		position = position.plus(velocity.scale(t).plus(acceleration.scale(0.5*t*t)));
		velocity = velocity.plus(acceleration.scale(t));
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass())return false;
		Body  body = (Body) obj;
		return id == body.id;
	}
	
	public String toString() {
		return "{ \"id\": \""+id+"\", \"mass\": "+mass+", \"pos\": "+position
				+",\"vel\": "+velocity+", \"acc\": "+acceleration+" }";
	}
}
