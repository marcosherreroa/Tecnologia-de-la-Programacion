package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {
	private List<Builder<T>> builders;
	
     public BuilderBasedFactory(List<Builder<T>> builders) {
    	 this.builders = builders;
     }
     
     public T createInstance(JSONObject info) {
    	 T element = null;
    	 boolean found = false;
    	 
    	 ListIterator<Builder<T>> it = builders.listIterator();
    	 while(!found && it.hasNext()) {
    		element = it.next().createInstance(info);
    		if(element != null)found = true;
    	 }
    	 
    	 if(!found)throw new IllegalArgumentException(": unknown type");
    	 
    	 return element;
     }
     
     public List<JSONObject> getInfo() {
    	 List<JSONObject> info = new ArrayList<JSONObject>(builders.size());
    	 ListIterator<Builder<T>> it = builders.listIterator();
    	 while(it.hasNext()) {
    		 info.add(it.next().getBuilderInfo());
    	 }
    	 
    	 return info;
     }
}
