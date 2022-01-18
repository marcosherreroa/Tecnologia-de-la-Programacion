//Flavius Ciapsa y Marcos Herrero

package tp.p3.exceptions;

public class CommandParseException extends Exception{
	private static final long serialVersionUID = 1L;

	public CommandParseException(String msg){
    	super(msg);
    }
	
	public CommandParseException (String msg, Exception cause){
		super(msg, cause);
	}
}
