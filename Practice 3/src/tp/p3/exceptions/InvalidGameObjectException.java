//  y  

package tp.p3.exceptions;

public class InvalidGameObjectException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidGameObjectException(String msg){
    	super(msg);
    }
	
	public InvalidGameObjectException(String msg, Exception cause){
		super(msg, cause);
	}
}
