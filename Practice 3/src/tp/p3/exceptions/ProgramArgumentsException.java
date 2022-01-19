//  y  

package tp.p3.exceptions;

public class ProgramArgumentsException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProgramArgumentsException(String msg){
    	super(msg);
    }
	
	public ProgramArgumentsException(String msg, Exception cause){
		super(msg, cause);
	}
}
