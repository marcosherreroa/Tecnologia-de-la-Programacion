//Flavius Ciapsa y Marcos Herrero

package tp.p1.logic.objects.plants;

import tp.p1.logic.Game;
import tp.p1.logic.objects.GameObject;
import tp.p1.logic.objects.Position;


public abstract class Plant extends GameObject{
      private int cost;
      
      public Plant(Position pos, int life, int damage, int cyclesToAct, Game game,
    		  String objectText, String objectShortText, int cost){
    	  super(pos, life, damage,cyclesToAct, -1, game,objectText, objectShortText);
    	  this.cost = cost;
      }
      
      public int getCost(){
    	  return cost;
      }
      
      public String helpText(){
    	   	 return (objectText +": Cost: "+cost+" suncoins  Damage: "+ damage);
    	    }
      
      public boolean action2(){
    	  return false;
      }
      
}
