//  y  

package tp.p1;

public enum Level {
    EASY("EASY",3,0.1), HARD("HARD",5,0.2), INSANE("INSANE", 10,0.3);
    
    private final String levelName;
    private final int zombiesNumber;
    private final double frequency;
    
    private Level(String levelName, int zombiesNumber, double frequency){
    	this.levelName = levelName;
    	this.zombiesNumber = zombiesNumber;
    	this.frequency = frequency;
    }
    
    public String getLevelName(){
    	return levelName;
    }
    public int getZombiesNumber(){
    	return zombiesNumber;
    }
    
    public double getFrequency(){
    	return frequency;
    }
    
    public Level parse(String name){
    	if(levelName.equals(name)) return this;
    	else return null;
    }
    
    public static Level parseLevel(String name){
    	Level[] availableLevels = Level.values();
    	Level level = null;
    	boolean found = false;
    	
    	for(int i= 0; !found && i< availableLevels.length; ++i){
    		level = availableLevels[i].parse(name);
    		if(level != null)found = true;
    	}
    	
    	if(level == null) return EASY;
    	else return level;
    }
}
