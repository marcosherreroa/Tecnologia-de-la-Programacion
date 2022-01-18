//Flavius Ciapsa y Marcos Herrero

package tp.p1.logic.objects.zombies;



public class AthleteZombie extends Zombie{
	public static final int LIFE = 2;
    public static final int CYCLESADVANCE = 1;
    
    public static final String OBJECTTEXT = "Athlete zombie";
    public static final String OBJECTSHORTTEXT = "X";
    
    
    public AthleteZombie(){
    	super(null, LIFE, CYCLESADVANCE, null, OBJECTTEXT, 
    			OBJECTSHORTTEXT);
    }
}
