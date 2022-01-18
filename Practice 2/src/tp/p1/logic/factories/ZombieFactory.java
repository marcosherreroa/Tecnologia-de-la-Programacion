//Flavius Ciapsa y Marcos Herrero

package tp.p1.logic.factories;

import java.util.Random;

import tp.p1.logic.objects.zombies.AthleteZombie;
import tp.p1.logic.objects.zombies.BucketheadZombie;
import tp.p1.logic.objects.zombies.CommonZombie;
import tp.p1.logic.objects.zombies.Zombie;

public class ZombieFactory {
    public static Zombie[] availableZombies ={
    		new CommonZombie(),
    		new AthleteZombie(),
    		new BucketheadZombie()
    };
   
	public static Zombie getZombie(String zombieName){
    	Zombie zombie = null;
    	
    	switch(zombieName) {
    	case "deportista":
    	case "x": zombie = new AthleteZombie(); break;
    	case "comun":
    	case "z": zombie = new CommonZombie(); break;
    	case "caracubo":
    	case "w": zombie = new BucketheadZombie(); break;
    	}
    	return zombie;
    }
	
	public static Zombie getRandomZombie(Random rand){
		Zombie zombie = null;
		int n = rand.nextInt(availableZombies.length);
		
		switch(n){
		case 0: zombie = new CommonZombie(); break;
		case 1: zombie = new AthleteZombie(); break;
		case 2: zombie = new BucketheadZombie(); break;
		}
		
		return zombie;
	}
    
    public static String listOfAvailableZombies() {
    StringBuilder str= new StringBuilder();	
    for(Zombie zombie: availableZombies){
    	str.append(zombie.helpText()).append(System.lineSeparator());
    }
    
    return str.toString();
    }
}

