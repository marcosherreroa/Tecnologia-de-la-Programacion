package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {
	public static final String TYPE = "ftcg";
	public static final String DESC = "Bodies fall towards origin";
	public static final String[] REQUIREDDATAKEYS = {};
	public static final String[] OPTIONALDATAKEYS = {};
	
	 public FallingToCenterGravityBuilder(){
		 super(TYPE, DESC , REQUIREDDATAKEYS, OPTIONALDATAKEYS);
	 };
	 
     public FallingToCenterGravity createTheInstance(JSONObject data) {
    	 return new FallingToCenterGravity();
     }
     
     public JSONObject createData() {
    	 return new JSONObject();
     }
}
