//  y  

package tp.p3.logic.objects.plants;

public class Peashooter extends Plant {
    public static final int LIFE = 3;
    public static final int COST = 50;
    public static final int CYCLESSHOOT = 1;
    public static final int SHOTSPERCYCLE = 1;
    public static final int DAMAGEPERSHOT = 1;
    
    
    public static final String OBJECTTEXT = "Peashooter";
    public static final String OBJECTSHORTTEXT = "P";
    
    
    public Peashooter(){
    	super(null, LIFE, DAMAGEPERSHOT, CYCLESSHOOT, null,
    			OBJECTTEXT, OBJECTSHORTTEXT, COST);
    }
    
	public boolean action1(){
				   game.shoot(pos, damage);	
				   return true;
		}
}
