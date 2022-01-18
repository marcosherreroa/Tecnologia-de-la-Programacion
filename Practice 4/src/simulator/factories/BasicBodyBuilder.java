package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class BasicBodyBuilder extends Builder<Body> {
	public static final String TYPE = "basic";
	public static final String DESC = "Physic entity";
	public static final String[] REQUIREDDATAKEYS = {"id", "pos", "vel", "mass"};
	public static final String[] OPTIONALDATAKEYS = {};
	
	 public BasicBodyBuilder(){
		 super(TYPE, DESC , REQUIREDDATAKEYS, OPTIONALDATAKEYS);
	 };
	 
     public Body createTheInstance(JSONObject data) {
    	 if(data.getJSONArray("pos").length() != PhysicsSimulator.DIMENSION) throw new IllegalArgumentException(": position vector must have size "+PhysicsSimulator.DIMENSION);
    	 if(data.getJSONArray("vel").length() != PhysicsSimulator.DIMENSION) throw new IllegalArgumentException(": velocity vector must have size "+PhysicsSimulator.DIMENSION);
    	 if(data.getDouble("mass") < 0)throw new IllegalArgumentException(": mass cannot be less than zero");
    	 
    	 String id = data.getString("id");
    	 Vector pos = new Vector(JSONToDoubleArray(data.getJSONArray("pos")));
    	 Vector vel = new Vector(JSONToDoubleArray(data.getJSONArray("vel")));
    	 Vector ac = new Vector(PhysicsSimulator.DIMENSION);
    	 double mass = data.getDouble("mass");
    	 
    	 return new Body(id, pos, vel, ac, mass);
     }
     
     public JSONObject createData() {
    	 JSONObject data = new JSONObject();
    	 data.put("id", "b1");
    	 
    	 double[] pos = {0.0e00, 0.0e00};
    	 data.put("pos", new Vector(pos));
    	 
    	 double[] vel = {0.05e04, 0.0e00};
    	 data.put("vel", new Vector(vel));
    	 
    	 data.put("mass", 5.97e24);
    	 return data;
     }
}
