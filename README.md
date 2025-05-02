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

The program is designed around a brute-force search approach. 

Major components:
 * CircuitTracer.java: Parses input, initializes the board, manages the search loop, and displays results.
 * CircuitBoard.java: Parses input files and provides utility methods like checking valid moves, getting the start/end points, and creating board copies.
 * TraceState.java: Represents a single path on the board, storing the path length, current position, and board state. It also generates next possible moves.
 * Storage<T>: Generic container class using either a stack or queue to store TraceState objects.

The program allows for easy switching between stack and queue based search via the Storage<T> class. This separation of concerns allows for a clean design.

TESTING:

Testing was conducted using the provided tester class, which included a series of input '.dat' files representing various valid circuit boards. The tester automatically ran the program using these files using both stack and queue storage strategies, verifying that the resulting paths were correct and, when using a queue, the shortest possible.

The test suite included boards with:
- Straightforward open paths
- Multiple branches requiring backtracking
- Large boards with many possible paths
- Boards with no valid solution
- Boards where the shortest path wasn't obvious

I recompiled and reran the full test suite multiple times after making changes, ensuring that my implementation passed all tests. No bugs remain, and the program behaves as expected under all tested scenarios.

DISCUSSION:

During development I faced lots of small issues, the main issue I was facing is that in "CircuitTracer Invalid Input File Tests" the first two tests were failling. I probably stared at the screen for atleast an hour. I kept making tweaks and small fixes here and there but I could not get the to pass. Then my roomate came in telling me he had finished his Circuit project and that he was having the same issue as me and all he did to fix it was recompile all the java files
 javac *.java 
then it worked. So naturally I recompiled my program and it worked. 

Another issue I faced was actually getting the CircuitTracer search algorithym to work. Luckily following the psudocode made things much easier.