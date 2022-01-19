//Alumnos:   
//           
package tp.p1;

public class SearchString {
    private boolean found;
    private String string;
    
    public SearchString(){
    	this.found = false;
    	this.string = "";
    }
    
	public boolean isFound() {
		return found;
	}
	
	public void setFound(boolean found) {
		this.found = found;
	}
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}
    
    
}
