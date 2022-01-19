//  y  

package tp.p3;

import java.util.Random;

import tp.p3.control.Controller;
import tp.p3.exceptions.InvalidLevelException;
import tp.p3.exceptions.ProgramArgumentsException;
import tp.p3.logic.Game;

public class PlantsVsZombies {
	public static void main(String[] args) {
		try {
			if (args.length != 1 && args.length != 2) {
				throw new ProgramArgumentsException("incorrect number of program arguments("+args.length+
						"), 1 or 2 expected");
			}

			Level level;
			try {
				level = Level.parse(args[0]);
			} catch (InvalidLevelException e) {
				throw new ProgramArgumentsException(e.getMessage(), e);
			}

			Random rand;
			int seed;
			if (args.length == 2) {
				try {
					seed = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					throw new ProgramArgumentsException(
							"the seed must be a number", e);
				}
			} else {
				Random randSeedGen = new Random();
				seed = randSeedGen.nextInt();
			}
			rand = new Random(seed);

			Game game = new Game(level, rand, seed);
			Controller controller = new Controller(game);
			controller.run();
		}

		catch (ProgramArgumentsException e) {
			System.out.print("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed] : "
					+ e.getMessage());
		}
	}
}
