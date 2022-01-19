//  y  

package tp.p3.exceptions;

public class InvalidLevelException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidLevelException(String msg){
    	super(msg);
    }
	
	public InvalidLevelException(String msg, Exception cause){
		super(msg, cause);
	}
}
