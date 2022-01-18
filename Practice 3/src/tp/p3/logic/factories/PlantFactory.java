//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic.factories;


import tp.p3.exceptions.InvalidGameObjectException;
import tp.p3.logic.objects.plants.CherryBomb;
import tp.p3.logic.objects.plants.Peashooter;
import tp.p3.logic.objects.plants.Plant;
import tp.p3.logic.objects.plants.Sunflower;
import tp.p3.logic.objects.plants.WallNut;



public class PlantFactory {
	public static Plant[] availablePlants = {
		    new Sunflower(),
			new Peashooter(),
			new CherryBomb(),
			new WallNut(),
	};
	
    public static Plant getPlant(String plantName) throws InvalidGameObjectException {
    	Plant plant = null;
        
    	switch(plantName.toLowerCase()){
    	case "s":
    	case "sunflower": plant = new Sunflower(); break;
    	case "p":
    	case "peashooter": plant = new Peashooter(); break;
    	case "n":
    	case "nuez": plant = new WallNut(); break;
    	case "c":
    	case "petacereza": plant = new CherryBomb(); break;
    	default: throw new InvalidGameObjectException("Unknown plant name : "+plantName);
    	}
    
    	return plant;
    }
    
    public static String listOfAvailablePlants() {
    StringBuilder str = new StringBuilder();	
    for(Plant plant: availablePlants){
    	str.append(plant.helpText()).append(System.lineSeparator());
    }
    
    return str.toString();
    }
}
