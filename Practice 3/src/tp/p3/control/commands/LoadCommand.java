//  y  

package tp.p3.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;
import tp.p3.MyStringUtils;

public class LoadCommand extends Command{
	String fileName;
	
	public static final String[] COMMANDNAMES = {"load", "ld"};
    public static final  String COMMANDTEXT = "Load <filename>";
    public static final String HELPTEXT = "Load the state of the game from a file.";
    public static final int NUMBEROFWORDS = 2;
	
    public LoadCommand(){
    	super(COMMANDNAMES, COMMANDTEXT, HELPTEXT, NUMBEROFWORDS);
    	this.fileName = null;
    }
    
    public LoadCommand parse(String[] commandWords) throws CommandParseException{
    	if (!checkName(commandWords[0])){
			return null;
		}
    	
	    checkNumberOfWords(commandWords.length);
	    
		fileName = commandWords[1];
		return this;
	}
    
    public boolean execute(Game game) throws CommandExecuteException{ //preguntarle a Carlos por los bloques try-with-resources
    	if(!MyStringUtils.isValidFilename(fileName)){
			throw new CommandExecuteException("Invalid filename: the filename contains invalid characters");
		}
    	
    	if(!MyStringUtils.fileExists(fileName)){
    		throw new CommandExecuteException("File not found");
    	}
    	
    	if(!MyStringUtils.isReadable(fileName)){
    		throw new CommandExecuteException("File is not readable");
    	}
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(fileName))){  
    		String line = br.readLine();
			if(!line.equals("Plants Vs Zombies v3.0")){
				throw new CommandExecuteException("Invalid file header: "+line);
			}
			game.load(br);
			System.out.println("Game successfully load from file " + fileName +System.lineSeparator());
		}
    	
    	catch(FileContentsException e){
    		throw new CommandExecuteException("Invalid content of file "+fileName+":"+e.getMessage());
    	}
		catch (IOException e) {
			throw new CommandExecuteException("Failed to read from file "+fileName);
		}
    	 
    	 return true;
    }
}
