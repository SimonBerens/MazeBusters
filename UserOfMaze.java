/**
  Test Maze class.

  Requires command line arguments:
  o  the name of a file containing a maze.
  o  the rank and file where an explorer is starting

  For example,
      java UserOfMaze mazes/4cell_treasureWest.txt -1 -1  # no explorer
 */

public class UserOfMaze {
    private static Displayer displayer;

    public static void main(String[] commandLine)
       throws java.io.FileNotFoundException {
        System.out.println();

        Maze maze = new Maze( commandLine[0]
                            , Integer.parseInt( commandLine[1])
                            , Integer.parseInt( commandLine[2])
                            );
        System.out.println( maze + System.lineSeparator());

        // moveTest( maze);
        // dropTest( maze);

        copyConstructTest( maze);

        // test Displayer
        // displayer = new Displayer( Integer.parseInt( commandLine[3]));
        // displayerTest( maze);

        // snapshotDemo( maze);
    }


    /**
      Move around a maze. Check the results.
      Run using a shell command like...
          java UserOfMaze mazes/4cell_treasureWest.txt -1 -1  # no explorer
     */
    private static void moveTest( Maze maze) {
        maze.go( Maze.EAST);
        System.out.println( "go east"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.go( Maze.NORTH);
        System.out.println( "go north"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.go( Maze.WEST);
        System.out.println( "go west"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.go( Maze.SOUTH);
        System.out.println( "go south"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());

        // step out of the maze
        maze.go( Maze.SOUTH);
        maze.go( Maze.SOUTH);
        System.out.println( "outside"
                          + ", leaving explorer \"on\" a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
    }


    /**
      Drop maze elements. Check the results.
      Run using a shell command like...
          java UserOfMaze mazes/4cell_treasureWest.txt 0 1
     */
    private static void dropTest( Maze maze) {
        maze.dropA( Maze.TREASURE);
        System.out.println( "tried to drop a " + Maze.TREASURE
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.dropA( Maze.WALL);
        System.out.println( "dropped a " + Maze.WALL
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.dropA( Maze.STEPPING_STONE);
        System.out.println( "dropped a " + Maze.STEPPING_STONE
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
    }


    /**
      Copy-construct a new maze and check its independence
      from the original.
      Run using a shell command like...
          java UserOfMaze mazes/intersection_treasureNorth.txt 1 1

     */
    private static void copyConstructTest( Maze old) {
        Maze copy = new Maze( old);

        // change the old
        old.go( Maze.NORTH);
        old.dropA( Maze.WALL);
        System.out.println(
                            "modified old" + System.lineSeparator()
                          + old + System.lineSeparator()
                          + "unchanged copy?" + System.lineSeparator()
                          + copy + System.lineSeparator()
                          );

        // change the copy
        copy.go( Maze.SOUTH);
        copy.go( Maze.WEST);
        copy.dropA( Maze.STEPPING_STONE);
        System.out.println(
                            "modified copy" + System.lineSeparator()
                          + copy + System.lineSeparator()
                          + "unchanged old?" + System.lineSeparator()
                          + old + System.lineSeparator()
                          );
    }


    /**
      Display changes to a maze.
      Run by using the height of your shell window as a final argument, like...
          java UserOfMaze mazes/4cell_treasureWest.txt 0 3 25
     */
    private static void displayerTest( Maze m) {
        int step = 0;

        displayer.atTopOfWindow( m + "step " + step++);

        // move past west edge, Displaying as we go
        while( step < 5) {
            m.go( Maze.WEST);
            displayer.atTopOfWindow( m + "step " + step++);
        }
    }


    /**
      Demo the restore-from-snapshot paradigm.
      Run using a shell command like...
          java UserOfMaze mazes/4cell_treasureWest.txt 0 1
     */
    private static void snapshotDemo( Maze candidate) {

        Maze snapshot;

        throw new java.lang.RuntimeException(
            "Write code to take a snapshot of @candidate. "
          + "Then, in @candidate, have the explorer go() out of the maze.");

        // System.out.println(
                            // "modified candidate with no explorer"
                          // + System.lineSeparator()
                          // + candidate + System.lineSeparator()
                          // + "unchanged snapshot" + System.lineSeparator()
                          // + snapshot + System.lineSeparator()
                          // );

        /* Expecting...
              modified candidate with no explorer
              ------
              |0** |
              ------

              unchanged snapshot
              ------
              |0e* |
              ------
         */

        // throw new java.lang.RuntimeException(
            // "Write code to undo the go() by making @candidate refer "
          // + "to an unchanged copy of the maze.");

        // System.out.println(
                            // "restored candidate, with an explorer"
                          // + System.lineSeparator()
                          // + candidate + System.lineSeparator()
                          // );
        /* Expecting...
              restored candidate, with an explorer
              ------
              |0e* |
              ------
         */
    }
}
