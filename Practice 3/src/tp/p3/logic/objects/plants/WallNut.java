//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic.objects.plants;

public class WallNut extends Plant {
	    public static final int LIFE = 10;
	    public static final int COST = 50;
	    
	    public static final String OBJECTTEXT = "Nuez";
	    public static final String OBJECTSHORTTEXT = "N";
	    
	    
	    public WallNut(){
	    	super(null, LIFE, 0, 0, null, OBJECTTEXT, OBJECTSHORTTEXT, COST);
	    }
	    
	    public boolean action1(){
	    	return false;
	    }
}
