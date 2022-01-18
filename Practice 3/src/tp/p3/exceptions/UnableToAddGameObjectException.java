//Falvius Ciapsa y Marcos Herrero
package tp.p3.exceptions;

public class UnableToAddGameObjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnableToAddGameObjectException(String msg){
    	super(msg);
    }
	
	public UnableToAddGameObjectException(String msg, Exception cause){
		super(msg, cause);
	}
}
