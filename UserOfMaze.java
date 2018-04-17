
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    // public static void main(String[] commandLine)
    //    throws java.io.FileNotFoundException {
    //     System.out.println();

    //     Maze maze = new Maze( commandLine[0]
    //                         , Integer.parseInt( commandLine[1])
    //                         , Integer.parseInt( commandLine[2])
    //                         );
    //     System.out.println( maze + System.lineSeparator());

    //     // moveTest( maze);
    //     // dropTest( maze);

    //     copyConstructTest( maze);

    //     // test Displayer
    //     // displayer = new Displayer( Integer.parseInt( commandLine[3]));
    //     // displayerTest( maze);

    //     // snapshotDemo( maze);
    // }

    public static void main(String[] commandLine) throws FileNotFoundException {
        
        System.out.println("If this is printed, pls set your filepath in the code and comment this section out");
        String filePath = "uhoh";
        
        // see https://piazza.com/class/j7oyiev6r7x576?cid=446
        // String filePath = "C:\\Users\\gbere\\Desktop\\AP_CS\\MazeSolver\\mazes"; // modify as necessary
        // String filePath = "C:\\Users\\lucie\\Documents\\School\\Git\\MazeBusters\\mazes";
         // String filePath = "E:\\Users\\Lucien\\Documents\\School\\Compsci\\Git\\MazeBusters\\mazes";
        String[] mazes = ( new File( filePath)).list();

        int option;
        do {
            showOptions( mazes);

            System.out.println( "Please select an option");
            test( validateInput( mazes.length), mazes);

            System.out.println( "Please select what you would like to do next \n" +
                    "0: Exit \n" +
                    "1: Test another maze \n");
            option = validateInput( 2);

        } while (option != 0);
    }

    private static void test(int choice, String[] mazes) throws FileNotFoundException {
        System.out.println( "Now testing " + mazes[ choice]);

        String fileName = "mazes\\" + mazes[ choice];

        // show the maze to solve
        try ( BufferedReader br = new BufferedReader( new FileReader( fileName))) {
            String line;
            while ( ( line = br.readLine()) != null) {
                System.out.println( line);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }

        // select starting position
        Scanner sc = new Scanner( System.in);
        System.out.println( "Where would you like to start?");
        System.out.println( "rank:");
        int rank = sc.nextInt();
        sc.nextLine();
        System.out.println( "file:");
        int file = sc.nextInt();

        // create and solve maze
        Maze maze = new Maze( fileName, rank, file);
        System.out.println( maze);
        moveTest( maze);
        dropTest( maze);
    }

    private static int validateInput( int range) {
        Scanner sc = new Scanner(System.in);

        int questionableChoice;
        do {
            System.out.println("Please enter a valid choice!");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next();
            }
            questionableChoice = sc.nextInt();
        } while (!(questionableChoice >= 0 && questionableChoice < range));
        return questionableChoice; // no longer questionable
    }

    /**
     * Displays the names of possible mazes to test
     * @param mazes the array of mazes
     */

    private static void showOptions( String[] mazes) {
        System.out.println( "Here are your options");
        System.out.println( mazes.length);
        for ( int i = 0; i < mazes.length; i++) {
            System.out.println( i + ": " + mazes[ i]);
        }
    }

    /**
      Move around a maze. Check the results.
      Run using a shell command like...
          java UserOfMaze mazes/4cell_treasureWest.txt -1 -1  # no explorer
     */
    private static void moveTest( Maze maze) {
        maze.go( Maze.Direction.EAST);
        System.out.println( "go east"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.go( Maze.Direction.NORTH);
        System.out.println( "go north"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.go( Maze.Direction.WEST);
        System.out.println( "go west"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.go( Maze.Direction.SOUTH);
        System.out.println( "go south"
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());

        // step out of the maze
        maze.go( Maze.Direction.SOUTH);
        maze.go( Maze.Direction.SOUTH);
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
        maze.dropA( Maze.Tile.TREASURE);
        System.out.println( "tried to drop a " + Maze.Tile.TREASURE
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.dropA( Maze.Tile.WALL);
        System.out.println( "dropped a " + Maze.Tile.WALL
                          + ", leaving explorer on a " + maze.explorerIsOnA()
                          +      System.lineSeparator()
                          + maze + System.lineSeparator());
        maze.dropA( Maze.Tile.STEPPING_STONE);
        System.out.println( "dropped a " + Maze.Tile.STEPPING_STONE
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
        old.go( Maze.Direction.NORTH);
        old.dropA( Maze.Tile.WALL);
        System.out.println(
                            "modified old" + System.lineSeparator()
                          + old + System.lineSeparator()
                          + "unchanged copy?" + System.lineSeparator()
                          + copy + System.lineSeparator()
                          );

        // change the copy
        copy.go( Maze.Direction.SOUTH);
        copy.go( Maze.Direction.WEST);
        copy.dropA( Maze.Tile.STEPPING_STONE);
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
            m.go( Maze.Direction.WEST);
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
