package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLosingBody;
import simulator.model.PhysicsSimulator;

public class MassLosingBodyBuilder extends Builder<Body> {
	public static final String TYPE = "mlb";
	public static final String DESC = "Physic entity that loses mass";
	public static final String[] REQUIREDDATAKEYS = {"id", "pos", "vel", "mass", "freq", "factor"};
	public static final String[] OPTIONALDATAKEYS = {};
	
	 public MassLosingBodyBuilder(){
		 super(TYPE, DESC , REQUIREDDATAKEYS, OPTIONALDATAKEYS);
	 };
	 
     public MassLosingBody createTheInstance(JSONObject data) {
    	 if(data.getJSONArray("pos").length() != PhysicsSimulator.DIMENSION) throw new IllegalArgumentException(": position vector must have size "+PhysicsSimulator.DIMENSION);
    	 if(data.getJSONArray("vel").length() != PhysicsSimulator.DIMENSION) throw new IllegalArgumentException(": velocity vector must have size "+PhysicsSimulator.DIMENSION);
    	 if(data.getDouble("mass") < 0)throw new IllegalArgumentException(": mass cannot be less than zero");
    	 
    	 String id = data.getString("id");
    	 Vector pos = new Vector(JSONToDoubleArray(data.getJSONArray("pos")));
    	 Vector vel = new Vector(JSONToDoubleArray(data.getJSONArray("vel")));
    	 Vector ac = new Vector(PhysicsSimulator.DIMENSION);
    	 double mass = data.getDouble("mass");
    	 double freq = data.getDouble("freq");
    	 double factor = data.getDouble("factor");
    	 
    	 return new MassLosingBody(id, pos, vel, ac, mass, factor, freq);
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
