//  y  

package tp.p3.logic.factories;

import java.util.Random;

import tp.p3.exceptions.InvalidGameObjectException;
import tp.p3.logic.objects.zombies.AthleteZombie;
import tp.p3.logic.objects.zombies.BucketheadZombie;
import tp.p3.logic.objects.zombies.CommonZombie;
import tp.p3.logic.objects.zombies.Zombie;

public class ZombieFactory {
    public static Zombie[] availableZombies ={
    		new CommonZombie(),
    		new AthleteZombie(),
    		new BucketheadZombie()
    };
   
	public static Zombie getZombie(String zombieName) throws InvalidGameObjectException{
    	Zombie zombie = null;
    	
    	switch(zombieName.toLowerCase()) {
    	case "deportista":
    	case "x": zombie = new AthleteZombie(); break;
    	case "comun":
    	case "z": zombie = new CommonZombie(); break;
    	case "caracubo":
    	case "w": zombie = new BucketheadZombie(); break;
    	default: throw new InvalidGameObjectException("Unknown zombie name : "+zombieName);
    	}
    	
    	return zombie;
    }
	
	public static Zombie getRandomZombie(Random rand){
		Zombie zombie = null;
		int n = rand.nextInt(availableZombies.length);
		try {
			zombie = getZombie(availableZombies[n].getObjectShortText());
		} catch (InvalidGameObjectException e) {
			System.out.println("Random error");
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

