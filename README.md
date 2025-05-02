DoubleLinkedList

Documentation (README, javadocs)                        10 / 20
ListTester class                                        20 / 20
Instructor's Tests                                      49 / 50
code formatting, conventions, encapulation, other       10 / 10

Total                                                   89 / 100

---------------------------------------------------------------

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



INCLUDED FILES:

 * README - this file

ANALYSIS:

Before you starting coding your project:
Use this simple layout to work completely through the search algorithm by hand twice -- once with stack storage, and once with queue storage

1 O O O O
O O O O 2

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
    The worst case runtime is O(n^2)

7. What does the order reflect? (Maximum size of Storage? Number of board positions? Number of paths explored? Maximum path length? Something else?)

What is 'n', the single primary input factor that increases the difficulty of the task?




COMPILING AND RUNNING:


PROGRAM DESIGN AND IMPORTANT CONCEPTS:


TESTING:



DISCUSSION:
