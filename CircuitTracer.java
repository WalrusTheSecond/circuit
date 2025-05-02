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
		if (args.length != 3) {
			printUsage();
			return; //exit the constructor immediately
		}
		
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
		CircuitBoard board;

		try{
			board = new CircuitBoard(filename);
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
		
		Storage<TraceState> stateStore = useStack ? Storage.getStackInstance() : Storage.getQueueInstance();
		ArrayList<TraceState> bestPaths = new ArrayList<>();
		Point start = board.getStartingPoint();
		int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

		for (int[] dir : directions) {
			int newRow = start.x + dir[0];
			int newCol = start.y + dir[1];
			if (board.isOpen(newRow, newCol)) {
				try {
					stateStore.store(new TraceState(board, newRow, newCol));
				} catch (Exception e) {
				}
			}
		}
	
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
		if (useConsole) {
			for (TraceState ts : bestPaths) {
				System.out.println(ts);
			}
		} else {
			System.out.println("GUI output not implemented.");
		}
	}
	
} // class CircuitTracer
