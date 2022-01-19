//Alumnos:   
//           
package tp.p1;

public class SuncoinManager {
    private int suncoins;
    
    public static final int STARTINGSUNCOINS = 50;
    public SuncoinManager(){
    	this.suncoins = STARTINGSUNCOINS;
    }

	public int getSuncoins() {
		return suncoins;
	}

	public void setSuncoins(int suncoins) {
		this.suncoins = suncoins;
	}
    
    public void addSuncoins (int suncoins){
    	this.suncoins += suncoins;
    }
    
    public void substractSuncoins (int suncoins){
    	this.suncoins -= suncoins;
    }
}
