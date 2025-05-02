import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** Launch the program. 
	 * 
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); //create this with args
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		System.out.println("Usage: javac CircuitTracer.java");
		System.out.println("Usage: java CircuitTracer [-s | -q] [-c | -g] <filename>");

		System.out.println(" -s: Use a stack for storage");
		System.out.println(" -q: Use a queue for storage");
		System.out.println(" -c: Output results to console");
		System.out.println(" -g: Output results to GUI(Not implemented)");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		///TODO: parse and validate command line args - first validation provided
		if (args.length != 3) {
			printUsage();
			return; //exit the constructor immediately
		}
		//TODO: initialize the Storage to use either a stack or queue
		boolean useStack = false;
		boolean useConsole = false;

		switch (args[0]) {
			case "-s":
				useStack = true;
				break;
			case "-q":
				useStack = false;
				break;
			default:
				printUsage();
				return;
		}


		switch (args[1]) {
    		case "-c":
        		useConsole = true;
        	break;
    		case "-g":
        		useConsole = false;
        	break;
    		default:
        	printUsage();
        	return;
		}

		String filename = args[2];
		//TODO: read in the CircuitBoard from the given file
		CircuitBoard board;
		try{
			board = new CircuitBoard(filename);
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
		
		//TODO: run the search for best paths
		//initialize an empty Storage object called stateStore that stores objects of type TraceState
		Storage<TraceState> stateStore = useStack ? Storage.getStackInstance() : Storage.getQueueInstance();

		//initialize an empty List called bestPaths that stores objects of type TraceState
		ArrayList<TraceState> bestPaths = new ArrayList<>();

		//add a new initial TraceState object (a path with one trace) to stateStore for each open position adjacent to the starting component
		//while (!stateStore.isEmpty)
		//retrieve the next TraceState object from stateStore
		//if that TraceState object is a solution (ends with a position adjacent to the ending component),
		//if bestPaths is empty or the TraceState object's path is equal in length to one of the TraceStates in bestPaths,
		//add it to bestPaths
		//else if that TraceState object's path is shorter than the paths in bestPaths,
		//clear bestPaths and add the current TraceState as the new shortest path
		//else generate all valid next TraceState objects from the current TraceState and add them to stateStore

		Point start = board.getStartingPoint();
		int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

		for (int[] dir : directions) {
			int newRow = start.x + dir[0];
			int newCol = start.y + dir[1];
			if (board.isOpen(newRow, newCol)) {
				try {
					stateStore.store(new TraceState(board, newRow, newCol));
				} catch (Exception e) {
					// Safe to ignore due to prior isOpen check
				}
			}
		}
	
		// Search algorithm
		while (!stateStore.isEmpty()) {
			TraceState current = stateStore.retrieve();
	
			if (current.isSolution()) {
				if (bestPaths.isEmpty()) {
					bestPaths.add(current);
				} else if (current.pathLength() < bestPaths.get(0).pathLength()) {
					bestPaths.clear();
					bestPaths.add(current);
				} else if (current.pathLength() == bestPaths.get(0).pathLength()) {
					bestPaths.add(current);
				}
			} else {
				for (int[] dir : directions) {
					int newRow = current.getRow() + dir[0];
					int newCol = current.getCol() + dir[1];
					if (current.isOpen(newRow, newCol)) {
						try {
							stateStore.store(new TraceState(current, newRow, newCol));
						} catch (Exception e) {
						}
					}
				}
			}
		}
		//TODO: output results to console or GUI, according to specified choice
		if (useConsole) {
			for (TraceState ts : bestPaths) {
				System.out.println(ts);
			}
		} else {
			// GUI output can be implemented here
			System.out.println("GUI output not implemented.");
		}
	}
	
} // class CircuitTracer
