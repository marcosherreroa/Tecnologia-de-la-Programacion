package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {
	public static final String TYPE = "nlug";
	public static final String DESC = "Newton's law of universal gravitation";
	public static final String[] REQUIREDDATAKEYS = {};
	public static final String[] OPTIONALDATAKEYS = {};
	
	 public NewtonUniversalGravitationBuilder(){
		 super(TYPE, DESC , REQUIREDDATAKEYS, OPTIONALDATAKEYS);
	 };
	 
     public NewtonUniversalGravitation createTheInstance(JSONObject data) {
    	 return new NewtonUniversalGravitation();
     }
     
     public JSONObject createData() {
    	 return new JSONObject();
     }
}
