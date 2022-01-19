//  y  

package tp.p1;

import java.util.Random;

import tp.p1.control.Controller;
import tp.p1.logic.Game;

public class PlantsVsZombies {
   public static void main(String[] args){
	   Level level = Level.parseLevel(args[0].toUpperCase());
	   Random rand;
	   int seed ;
	   
	   if (args.length > 1)seed = Integer.parseInt(args[1]);		   
	   else {
		   Random randSeedGen = new Random();
		   seed = randSeedGen.nextInt();
	   }	   
	   rand = new Random(seed);
	   
	   Game game = new Game(level, rand, seed);
	   Controller controller = new Controller(game);
	   controller.run();
   }
}

