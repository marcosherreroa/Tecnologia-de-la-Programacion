//  y  

package tp.p3.logic.objects.zombies;

public class CommonZombie extends Zombie {
	public static final int LIFE = 5;
    public static final int CYCLESADVANCE = 2;
    
    public static final String OBJECTTEXT = "Common zombie";
    public static final String OBJECTSHORTTEXT = "Z";
    
    
    public CommonZombie(){
    	super(null, LIFE, CYCLESADVANCE, null, OBJECTTEXT, 
    			OBJECTSHORTTEXT);
    }
    
}
