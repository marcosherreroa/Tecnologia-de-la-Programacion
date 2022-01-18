//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa
package tp.p1;

import java.util.Random;

public class PlantsVsZombies {

   public static void main(String[] args){
	   Level level = Level.assignLevel(args[0].toUpperCase());
	   
	   Random rand;
	   if (args.length > 1){
		   System.out.println("Random seed used: "+args[1]);
		   rand = new Random(Long.parseLong(args[1]));
	   }
	   else rand = new Random();
	   
	   Game game = new Game(level, rand);
	   Controller controller = new Controller(game);
	   controller.run();
   }
}
