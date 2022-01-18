//Flavius Ciapsa y Marcos Herrero

package tp.p3.exceptions;

public class FileContentsException extends Exception{
	private static final long serialVersionUID = 1L;

	public FileContentsException(String msg){
    	  super(msg);
      }
      
      public FileContentsException(String msg, Exception cause){
    	  super(msg, cause);
      }
}
