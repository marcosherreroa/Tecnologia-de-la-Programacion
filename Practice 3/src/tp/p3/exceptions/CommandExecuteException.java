//Flavius Ciapsa y Marcos Herrero

package tp.p3.exceptions;

public class CommandExecuteException extends Exception {
	private static final long serialVersionUID = 1L;

	public CommandExecuteException(String msg){
		super(msg);
	}

	public CommandExecuteException(String msg, Exception cause){
    	super(msg, cause);
    }
}
