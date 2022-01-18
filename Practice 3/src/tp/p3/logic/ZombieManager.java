//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic;

import java.util.Random;

import tp.p3.Level;

public class ZombieManager {
	private int zombiesLeft;
	private double frequency;
   private final Random rand;
   
   public ZombieManager(Level level , Random rand){
	  this.zombiesLeft = level.getZombiesNumber();
      this.frequency = level.getFrequency();
	   this.rand = rand;
   }
   
   public int getZombiesLeft() {
	return zombiesLeft;
}

   public void setZombiesLeft(int zombiesLeft) {
	this.zombiesLeft = zombiesLeft;
}
   
   public void substractZombiesLeft(int zombies){
	   this.zombiesLeft -= zombies;
   }

public boolean isZombieAdded(){
	   if(zombiesLeft == 0) return false;
	   else {
		   double p = rand.nextDouble();
		   if(p < frequency)return true;
		   else return false;
       }
}
}

