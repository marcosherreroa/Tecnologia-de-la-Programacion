//Alumnos:   
//           
package tp.p1;

public enum Level {
    EASY(3, 0.1), HARD(5, 0.2), INSANE(10, 0.3);
	
	private int zombiesNumber;
	private double zombiesFreq;
	
	private Level(int zombiesNumber, double zombiesFreq) {
		this.zombiesNumber = zombiesNumber;
		this.zombiesFreq = zombiesFreq;
	}
	
	public static Level assignLevel(String levelName) {
		switch(levelName){
		   case "EASY": return EASY;
		   case "HARD": return HARD;
		   case "INSANE": return INSANE;
		   default: return EASY;
		   }
	}
	
	public int getZombiesNumber() {
		return this.zombiesNumber;
	}
	
	public double getZombiesFreq() {
		return this.zombiesFreq;
	}
}
