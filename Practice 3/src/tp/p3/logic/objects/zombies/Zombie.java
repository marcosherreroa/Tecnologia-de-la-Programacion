//  y  

package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Position;

public abstract class Zombie extends GameObject{
	public static final int CYCLESATTACK= 1;
	public static final int DAMAGEPERHIT = 1;
	
    public Zombie(Position pos, int life, int cyclesAct1, Game game
    		, String objectText, String objectShortText) {
    	super(pos, life, DAMAGEPERHIT ,cyclesAct1, CYCLESATTACK, game, objectText, 
    			objectShortText);
	}
    
    public String helpText(){
    	return (objectText +" : Speed: "+cyclesAct1+" Damage: "+ damage);
    }
   
    public boolean action1(){    //Advance
    		boolean advance = false;
    		advance = game.checkAdvance(pos);
			if(advance) pos.substractY(1);
			return advance;
    }
    
    public boolean action2(){  //Attack
    	   game.attackPlants(pos, DAMAGEPERHIT);
    	   return true;
    }
     
}
