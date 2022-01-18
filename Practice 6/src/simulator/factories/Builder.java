package simulator.factories;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {
	protected final String type;
	protected final String desc;
	protected final Set<String> requiredKeySet;
	protected final Set<String> optionalKeySet;
	protected final Set<String> requiredDataKeySet;
	protected final Set<String> optionalDataKeySet;
	
	public static final String[] REQUIREDKEYS = {"type", "data"};
	public static final String[] OPTIONALKEYS = {"desc"};

	public Builder(String type,String desc, String[] requiredDataKeys, String[] optionalDataKeys){
		this.type= type;
		this.desc = desc;
		this.requiredKeySet = new HashSet<String>(Arrays.asList(REQUIREDKEYS));
		this.optionalKeySet = new HashSet<String>(Arrays.asList(OPTIONALKEYS));
		this.requiredDataKeySet = new HashSet<String>(Arrays.asList(requiredDataKeys));
		this.optionalDataKeySet = new HashSet<String>(Arrays.asList(optionalDataKeys));
	}
	
    public T createInstance(JSONObject info){
    	if(! info.getString("type").equals(type)) return null;
    	
    	Set<String> jobkeys = info.keySet();
    	Iterator<String> it = jobkeys.iterator();
    	String s;
    	while(it.hasNext()) { 
    		s = it.next();
    		if(!requiredKeySet.contains(s) && !optionalKeySet.contains(s)) {
    			throw new IllegalArgumentException(": unknown main key : "+s);
    		}
    	}
    	
    	it = requiredKeySet.iterator();
    	while(it.hasNext()) {
    		s = it.next();
    		if(!jobkeys.contains(s)) {
    			throw new IllegalArgumentException(": missing required main key : "+s);
    		}
    	}
    	
    	Set<String> jobDataKeys = info.getJSONObject("data").keySet();
    	it = jobDataKeys.iterator();
    	while(it.hasNext()) {
    		s = it.next();
    		if(!requiredDataKeySet.contains(s) && !optionalDataKeySet.contains(s)) {
    			throw new IllegalArgumentException(": unknown data key for type "+ type + " : "+s);
    		}
    	}
    	
    	it = requiredDataKeySet.iterator();
    	while (it.hasNext()) {
    		s = it.next();
    		if(!jobDataKeys.contains(s)) {
    			throw new IllegalArgumentException(": missing required data key for type"+ type +" : "+s);
    		}
    	}
    	
    	return createTheInstance(info.getJSONObject("data"));
    }
    
    public JSONObject getBuilderInfo() {
    	JSONObject info = new JSONObject();
    	info.put("type", type );
    	info.put("data", createData());
    	info.put("desc", desc);
    	return info;
    }
    
    public double[] JSONToDoubleArray(JSONArray J){
    	double[] list = new double[J.length()];
   
    	for(int i = 0; i< J.length(); ++i) list[i] = J.getDouble(i);
    	return list;
    }
    
    public abstract T createTheInstance(JSONObject data);
    public abstract JSONObject createData();
}
