//  y  

package tp.p3;

import tp.p3.exceptions.InvalidLevelException;

public enum Level {
    EASY(3,0.1), HARD(5,0.2), INSANE(10,0.3);
    
    private final int zombiesNumber;
    private final double frequency;
    
    private Level(int zombiesNumber, double frequency){
    	this.zombiesNumber = zombiesNumber;
    	this.frequency = frequency;
    }
    
    public int getZombiesNumber(){
    	return zombiesNumber;
    }
    
    public double getFrequency(){
    	return frequency;
    }
    
    public static String all (String separator) {
    	StringBuilder sb = new StringBuilder();
    	for (Level level : Level.values() )
    	sb.append(level.name() + separator);
    	String allLevels = sb.toString();
    	return allLevels.substring(0, allLevels.length() - separator.length());
    	}
    
    public static Level parse(String name) throws InvalidLevelException{
    	Level level = null;
    	boolean found = false;
    	
    	for(int i= 0; !found && i< Level.values().length; ++i){
    		if (Level.values()[i].name().equalsIgnoreCase(name)){
    			level = Level.values()[i];
    			found = true;
    		}
 
    	}
    	
    	if(!found) throw new InvalidLevelException("invalid level("+name+
    			"), level must be one of: "+ all(", ")+System.lineSeparator());
    	else return level;
    }
}
