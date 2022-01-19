//  y  

package tp.p3.logic.list;

public class SearchString {
    private final boolean found;
    private final String string;
    
    public SearchString(boolean found,String string){
    	this.found = found;
    	this.string = string;
    }
    
	public boolean isFound() {
		return found;
	}
	
	public String getString() {
		return string;
	}
 
}
