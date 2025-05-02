
README
proofreading
design/concepts - interfaces? node class? relationships between classes unclear - why a dll? strengths/weaknesses vs alts?
testing - test class? scenarios? bugs?

missing tester @author

missing IUDLL class javadoc

missing constructor javadoc

leftover TODO

missing ListIterator class javadoc

missing constructor javadocs


****************
* Circuit
* Computer Science 221 Tuesday/Thursday 12 pm
* 5/2/2025
* Evan Wallace
**************** 

OVERVIEW:

This program finds the shortest trace path between two components on a circuit board, using either stack based or queue based search to explore all possible paths and identify the optimal one(s).


INCLUDED FILES:

 * CircuitTracer.java - Main driver class that loads the board, performs the  search, and outputs results.
 * CircuitBoard.java - Represents the circuit layout, handles file input, and provides utility methods.
 * CircuitTracerTester.java - The tester class that fully tests and esures the functionality of CircuitTracer.java.
 * TraceState.java - Represents a path traced so far; stores the current position and board state.
 * Storage.java - Generic container using either a stack or a queue for storing TraceStates.
 * InvalidFileFormatException.java - A custom exception used in detecting improperly formatted circuit board files.
 * OccupiedPositionException.java - A custom exception used when trying to trace over an already occupied board position.
 * valid1.dat through valid10.dat - Sample circuit board files for testing.
 * README - This file.

ANALYSIS:

1. How does the choice of Storage configuration (stack vs queue) affect the sequence in which paths are explored in the search algorithm? (This requires more than a "stacks are LIFOs and queues are FIFOs" answer.)
    STACK:
     The most recently added TraceState is processed next, so the algorithm goes deep down one path before bcktracking. This may find a solution quickly but not necessarily the shortest one.
    QUEUE:
     The earliest added TraceState is processed first, so all paths of length 1 are explored before any paths of length 2, and so on. This guarantees that the first found solution is the shortest.
    So stack dives deep into fewer paths quickly, while queue spreads out and builds solutions layer by layer.

2. Is the total number of search states (possible paths) affected by the choice of stack or queue?
    The total number of search states (possible paths) are not affected by the choice of stack or queue.

3. Is using one of the storage structures likely to find a solution in fewer steps than the other? Always?
    Stack is more likely to find a solution in fewer steps because it follows one path rather than expanding paths by distance. It will likely not be the fastest solution, but it is a solution.

4. Does using either of the storage structures guarantee that the first solution found will be a shortest path?
    QUEUE:
     The queue storage structure guaratees that the first solution found is the shortests path. It explores shortest length paths first.
    STACK:
     The stack storage structure cannot guarantee that the first solution is the shortest path. It explroes the first path then moves on.

5. How is memory use (the maximum number of states in Storage at one time) affected by the choice of underlying structure?
    QUEUE:
     Queue will often require more memory, because it explores all paths of a certain length as once. This is magnefied especially on larger boards.
    STACK:
     Stack will typically use less memory, because it goes deep on one path, using minimal memory until it backtracks.
    So the stack based storage structure is more memory efficient but less time efficient for finding the shortest path.

6. What is the Big-Oh runtime order for the search algorithm?
     The worst case runtime is O(3^n), '3' is the amount of new positions from a given position assuming you were just at the previous one. 'n' is the total number of open spaces that can be part of a trace.

7. What does the order reflect? (Maximum size of Storage? Number of board positions? Number of paths explored? Maximum path length? Something else?)
     The order reflects the number of paths explored. The number of paths explored is greatly influenced by a variety of factors. These factors are:
         -Board size (The number of open positions)
         -Path length
         -The obstacles in the way
         -The structure of the branching (Using either queue or stack storage)

What is 'n', the single primary input factor that increases the difficulty of the task?

     'n' is the size of the board, or the number of open positions that can be part of a path. Larger boards with more open positions allow more possible paths, which is the main factor for increasing the number of states and computation time.

COMPILING AND RUNNING:

To compile from the command line, navigate to the directory with all the .java files and run:
 javac CircuitTracer.java
To run circuit tracer
 $ java CircuitTracer [-s | -q] [-c | -g] filename.dat
 * The first arg is used to select stack or queue: -s for stack or -q for queue
 * The second arg is used to select console or GUI output: -c for console or -g for GUI(not implemented)
 * The final arg is the input for the file name. Example file input: 'valid1.dat'


PROGRAM DESIGN AND IMPORTANT CONCEPTS:



TESTING:



DISCUSSION:
