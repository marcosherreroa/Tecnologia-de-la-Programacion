//Flavius Ciapsa
//Marcos Herrero

package simulator.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

/*
 * Examples of command-line parameters:
 * 
 *  -h
 *  -i resources/examples/ex4.4body.txt -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl ftcg
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl nlug
 *
 */

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.BasicBodyBuilder;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.FallingToCenterGravityBuilder;
import simulator.factories.MassLosingBodyBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoGravityBuilder;
import simulator.misc.MyStringUtils;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.view.MainWindow;

public class Main {

	// default values for some parameters
	//
	private final static Double _dtimeDefaultValue = 2500.0;
	private final static OutputStream _outputDefaultValue = System.out;
	private final static Integer _numStepsDefaultValue = 150;
	private final static String _defaultMode = "batch";

	// some attributes to stores values corresponding to command-line parameters
	//
	private static Double _dtime = null;
	private static String _inFile = null;
	private static JSONObject _gravityLawsInfo = null;
	private static String _outFile = null;
	private static Integer _numSteps = null;
	private static String _mode = null;
	

	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<GravityLaws> _gravityLawsFactory;
	
	//builders
	private static List<Builder<Body>> bodyBuilderList;
	private static List<Builder<GravityLaws>> lawsBuilderList;
	
	private static void init() {
		bodyBuilderList = new ArrayList<Builder<Body>>();
		bodyBuilderList.add(new BasicBodyBuilder());
		bodyBuilderList.add(new MassLosingBodyBuilder());
		
		lawsBuilderList = new ArrayList<Builder<GravityLaws>>();
		lawsBuilderList.add(new NewtonUniversalGravitationBuilder());
		lawsBuilderList.add(new FallingToCenterGravityBuilder());
		lawsBuilderList.add(new NoGravityBuilder());
		
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilderList);
		_gravityLawsFactory = new BuilderBasedFactory<GravityLaws>(lawsBuilderList);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseDeltaTimeOption(line);
			parseGravityLawsOption(line);
			parseOutFileOption(line);
			parseNumStepsOption(line);
			parseModeOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		// help
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());

		// input file
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Bodies JSON input file.").build());

		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());

		// gravity laws -- there is a workaround to make it work even when
		// _gravityLawsFactory is null. 
		//
		String gravityLawsValues = "N/A";
		String defaultGravityLawsValue = "N/A";
		if (_gravityLawsFactory != null) {
			gravityLawsValues = "";
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gravityLawsValues.length() > 0) {
					gravityLawsValues = gravityLawsValues + ", ";
				}
				gravityLawsValues = gravityLawsValues + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}
			defaultGravityLawsValue = _gravityLawsFactory.getInfo().get(0).getString("type");
		}
		
		cmdLineOptions.addOption(Option.builder("gl").longOpt("gravity-laws").hasArg()
				.desc("Gravity laws to be used in the simulator. Possible values: " + gravityLawsValues
						+ ". Default value: '" + defaultGravityLawsValue + "'.")
				.build());

		//output file
		
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where output is written. Default "
				+ "value: the standard output").build());
		
		//numSteps
		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg().desc("An integer representing the number of " 
				+"simulation steps. Default value: "+_numStepsDefaultValue+".").build());
		
		//mode
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("Execution mode. Possible values: 'batch'"
		+"(Batch mode), \"gui\" (Graphical User Interface mode). Default Value: '"+_defaultMode+"'").build());
		
		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null && _mode == "batch") {
			throw new ParseException("An input file of bodies is required in batch mode");
		}
	}

	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	private static void parseGravityLawsOption(CommandLine line) throws ParseException {
		String gl = line.getOptionValue("gl");
		if (gl != null) {
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gl.equals(fe.getString("type"))) {
					_gravityLawsInfo = fe;
					break;
				}
			}
			if (_gravityLawsInfo == null) {
				throw new ParseException("Invalid gravity laws: " + gl);
			}
		} else {
			_gravityLawsInfo = _gravityLawsFactory.getInfo().get(0);
		}
	}

	
	private static void parseOutFileOption(CommandLine line) throws ParseException{
		if(line.hasOption("o")) {
		_outFile = line.getOptionValue("o");
		if(_outFile == null || !MyStringUtils.isValidFilename(_outFile)) { //arreglar o preguntarle a CarlosT
			throw new ParseException("Invalid output file name");
		}
		}
	}
	
	private static void parseNumStepsOption(CommandLine line) throws ParseException {
		String stepTime = line.getOptionValue("s", _numStepsDefaultValue.toString());
		try {
			_numSteps = Integer.parseInt(stepTime);
			assert (_numSteps>= 0);
		} catch (Exception e) {
			throw new ParseException("Invalid numSteps value: " + stepTime);
		}
	}
	
	private static void parseModeOption(CommandLine line) throws ParseException{
		_mode = line.getOptionValue("m");
		if(!_mode.equals("batch") && !_mode.equals("GUI")){
			throw new ParseException("Invalid mode");
		}
	}
	
	private static void startBatchMode() throws Exception {
		GravityLaws law = _gravityLawsFactory.createInstance(_gravityLawsInfo);
				
		PhysicsSimulator simulator = new PhysicsSimulator(_dtime, law);
		Controller controller = new Controller(simulator, _bodyFactory, _gravityLawsFactory);
		
		InputStream in = new FileInputStream(new File(_inFile));
		OutputStream out;
		if(_outFile != null) out= new FileOutputStream (new File(_outFile));
		else out = _outputDefaultValue;
		
		controller.loadBodies(in);
		controller.run(_numSteps, out);
	}
	
	private static void startGUIMode() throws Exception{
		GravityLaws law = _gravityLawsFactory.createInstance(_gravityLawsInfo);
		PhysicsSimulator simulator = new PhysicsSimulator(_dtime, law);
		
		Controller controller = new Controller(simulator, _bodyFactory, _gravityLawsFactory);
		
		if(_inFile != null) {
			InputStream in= new FileInputStream (new File(_inFile));
			controller.loadBodies(in);
		}
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
			   new MainWindow(controller);
			}
			});
	}

	private static void start(String[] args) throws Exception {
		parseArgs(args);
		
		switch(_mode) {
		case "batch": startBatchMode(); break;
		case "GUI": startGUIMode(); break;
		}
		
	}

	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
