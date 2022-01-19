//  y  

package tp.p1.logic.objects.zombies;



public class BucketheadZombie extends Zombie {
	public static final int LIFE = 8;
    public static final int CYCLESADVANCE = 4;
   
    public static final String OBJECTTEXT = "Buckethead zombie";
    public static final String OBJECTSHORTTEXT = "W";
    
    
    public BucketheadZombie(){
    	super(null, LIFE, CYCLESADVANCE, null,OBJECTTEXT, 
    			OBJECTSHORTTEXT);
    }
    
}
