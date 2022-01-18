package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {
	public static final String TYPE = "ng";
	public static final String[] REQUIREDDATAKEYS = {};
	public static final String[] OPTIONALDATAKEYS = {};
	
	public NoGravityBuilder(){
		super(TYPE, NoGravity.DESC , REQUIREDDATAKEYS, OPTIONALDATAKEYS);
	 };
	 
     public NoGravity createTheInstance(JSONObject data) {
    	 return new NoGravity();
     }
     
     public JSONObject createData() {
    	 return new JSONObject();
     }
}
