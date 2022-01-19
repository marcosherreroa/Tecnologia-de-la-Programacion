//  y  
package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	private PhysicsSimulator simulator;
    private Factory<Body> factory;
    
    public Controller(PhysicsSimulator simulator, Factory<Body> factory) {
  		this.simulator = simulator;
  		this.factory = factory;
  	}
    
    public void loadBodies(InputStream in) {
    	JSONObject jsonInput = new JSONObject(new JSONTokener(in));
    	
    	if(!jsonInput.has("bodies")) throw new IllegalArgumentException(": invalid input");
    	JSONArray bodies = jsonInput.getJSONArray("bodies");
    	Body b;
    	for(int i = 0; i< bodies.length(); ++i) {
    		b = factory.createInstance(bodies.getJSONObject(i));
    		simulator.addBody(b);
    	}
    }
    
    public void run(int n, OutputStream out) throws IOException {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("{");
    	sb.append(System.lineSeparator());
    	sb.append("\"states\": [");
    	sb.append(System.lineSeparator());
    	sb.append(simulator.toString());
    	for(int i = 1; i <= n; ++i) {
    		simulator.advance();
    		sb.append(",");
    		sb.append(System.lineSeparator());
    		sb.append(simulator.toString());
    	}
    	sb.append("]");
    	sb.append(System.lineSeparator());
    	sb.append("}");
    	
    	try {
			out.write(sb.toString().getBytes());
		} catch (IOException e) {
			throw new IOException("Couldn't write the data");
		}
    	
    }
    
}
