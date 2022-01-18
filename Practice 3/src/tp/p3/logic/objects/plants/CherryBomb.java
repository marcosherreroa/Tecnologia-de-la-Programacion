//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic.objects.plants;

public class CherryBomb extends Plant {
	    public static final int LIFE = 2;
	    public static final int COST = 50;
	    public static final int CYCLESEXPLODE = 2;
	    public static final int DAMAGEEXPLOSION = 10;
	    
	    public static final String OBJECTTEXT = "Cherrybomb";
	    public static final String OBJECTSHORTTEXT = "C";
	    
	    
	    public CherryBomb(){
	    	super(null, LIFE, DAMAGEEXPLOSION, CYCLESEXPLODE, 
	    			null,OBJECTTEXT, OBJECTSHORTTEXT, COST);
	    }
	   
	    public  boolean action1(){
	    		game.explode(pos, damage);
	    		life = 0;
	    		return true;
	    }
}
