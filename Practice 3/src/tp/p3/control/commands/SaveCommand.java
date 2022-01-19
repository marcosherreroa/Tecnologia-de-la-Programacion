//  y  

package tp.p3.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.MyStringUtils;

public class SaveCommand extends Command{
	String fileName;
	
	public static final String[] COMMANDNAMES = {"save", "s"};
    public static final  String COMMANDTEXT = "Save <filename>";
    public static final String HELPTEXT = "Save the state of the game to a file.";
    public static final int NUMBEROFWORDS = 2;
	
    public SaveCommand(){
    	super(COMMANDNAMES, COMMANDTEXT, HELPTEXT, NUMBEROFWORDS);
    	this.fileName = null;
    }
    
    public SaveCommand parse(String[] commandWords) throws CommandParseException{
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
		
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".dat"))){
			bw.write("Plants Vs Zombies v3.0");
			bw.newLine();
			game.store(bw);
			System.out.println("Game successfully saved in file " + 
			fileName + ".dat. Use the load command to reload it"+System.lineSeparator());
		}
    	
		catch (IOException e) {
			throw new CommandExecuteException("Failed to write in file "+fileName+".dat");
		}
    	 
    	 return false;
    }
}
