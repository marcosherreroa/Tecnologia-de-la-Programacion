//  y  

package tp.p1.logic.objects.plants;



public class Sunflower extends Plant {
    public static final int LIFE = 1;
    public static final int COST = 20;
    public static final int CYCLESSUN = 3;
    public static final int SUNSGEN = 10;
    
    public static final String OBJECTTEXT = "Sunflower";
    public static final String OBJECTSHORTTEXT = "S";
    
    
    public Sunflower(){
    	super(null, LIFE, 0, CYCLESSUN, null, OBJECTTEXT, OBJECTSHORTTEXT, COST);
    }
    
	public boolean action1(){
			game.generateSun(SUNSGEN);
			return true;
	}
	
}
