/**
  Represent a Maze with an Explorer in it
  
  A "MazeTerminal" is...
    o  a wall element; or
    o  a treasure; or
    o  a stepping stone.
  
  A "Maze" is...
    o  a MazeTerminal; or
    o  a stepping stone with a Maze as any of its 4 neighbors
  plus an optional explorer positioned on any element of the Maze.
 */
import java.util.Scanner;

public class Maze {

    // MazeTerminal named constants
    public final static int TREASURE = 0;
    public final static int WALL = 1;
    public final static int STEPPING_STONE = 2;
    
    // directions that can be searched
    public final static int EAST =  1;
    public final static int NORTH = 2;
    public final static int WEST =  4;
    public final static int SOUTH = 8;
       /* Values are pretty arbitrary. Values of 2^n might be useful
          in the unlikely event that we ever want to add north-west, etc.:
          2+4 --> 6  */
    
    private int[][] maze;
    private final static int MAX_RANKS = 64;
    private int rankCount;  // number of ranks in a constructed Maze
    
    private Vector explorerPosition;  // see Vector inner class, below

    /**
      Construct an instance from the contents of a file.
      For v0, maze is rectangular, with every line having the same length.
     */
    public Maze( String sourceFilename
               , int explorerRank, int explorerFile
               ) throws java.io.FileNotFoundException {

        /* Construct the maze array one rank at a time, in case
           we ever allow non-rectangular mazes  */
        maze = new int[ MAX_RANKS][];

        Scanner sc = new Scanner( new java.io.File( sourceFilename));
        sc.useDelimiter("");  // Whitespaces are data, not delimiters.

        // process the maze file
        while( sc.hasNextLine() ) {
            int rank = rankCount++;
            /* So rankCount == last rank +1, as usual.
               That is, rankCount is one larger than the number of
               the last-used rank.
             */
            String line = sc.nextLine();
            // System.out.println( "|" + line + "|");
            
            maze[ rank] = new int[ line.length()];

            // Convert the input line into maze elements.
            for( int file = 0; file < line.length(); file++ ) {
                String inChar = line.substring( file, file+1);
                int element;  // value destined for maze array
                if(      inChar.equals("0"))  element = TREASURE;
                else if( inChar.equals("*"))  element = STEPPING_STONE;
                // spaces and unrecognised characters are walls
                else                          element = WALL;
                maze[ rank][ file] = element;
            }
        }
        
        explorerPosition = new Vector().add( explorerRank, explorerFile);
        // // for debugging: report explorer's location
        // System.out.println( "explorer at " + explorerPosition.rank
                          // + ", " +           explorerPosition.file);
    }


    /**
      Copy-construct an instance.
      Deep copy of all instance fields.
     */
    public Maze( Maze old) {

        // Copy the explorer's position (code by Holmes is asserted to work)
        explorerPosition = new Vector( old.explorerPosition);
        this.rankCount = old.rankCount;
        maze = new int[rankCount][]; // *hopefully* works for non rectangular mazes
        for (int rank = 0; rank < rankCount; rank++) {
            int fileLength = old.maze[rank].length;
            maze[rank] = new int[fileLength];
            for (int file = 0; file < fileLength; file++) {
                maze[rank][file] = old.maze[rank][file];
            }
        }
    }


    }


    /**
      @return a string representing of this instance
     */
    public String toString() {
        
        /* characters that represent elements of the maze,
           indexed by the numbers used to represent elements
          */
        final String outChar = "0 *";  // no explorer here
        final String exOnTop = "!Ee";  /* explorer on top of
           treasure, wall, stepping stone, etc. */

        // build string for top and bottom separators
        String aboveAndBelow = "-";
        for( int file = 0; file < maze[0].length; file++)
            aboveAndBelow += "-";
        aboveAndBelow += "-" + System.lineSeparator();
        
        // process maze[][], explorer
        String result = aboveAndBelow;
        for( int rank = 0; rank < rankCount; rank++) {
            result += "|";
            for( int file = 0; file < maze[ rank].length; file++) {
                int elem = maze[ rank][ file];
                
                // choose from the appropriate character set,
                if(    explorerPosition != null
                    && explorerPosition.equals( rank, file)
                  )
                     result += exOnTop.substring( elem, elem+1);
                else result += outChar.substring( elem, elem+1);
            }
            result += "|" + System.lineSeparator();
        }
        return   result + aboveAndBelow;
    }


    /**
      Move the Explorer a step in the indicated direction.
      Attempting to position the explorer outside the maze means
      it has no position.
      
      @precondition: explorer starts in a valid position
     */
    public void go( int direction)  { 
        switch( direction) {
            case EAST:
                explorerPosition = explorerPosition.add( 0, 1);
                break;
            case NORTH:
                explorerPosition = explorerPosition.add( -1, 0);
                break;
            case WEST:
                explorerPosition = explorerPosition.add( 0, -1);
                break;
            case SOUTH:
                explorerPosition = explorerPosition.add( 1, 0);
                break;
        }
    }


    /**
      Modify the maze to have @mazeElement in the explorer's position.
      Nix dropping treasure.
     */
    public void dropA( int mazeElement) {
        if( mazeElement != TREASURE)
            maze[ explorerPosition.rank][ explorerPosition.file] = mazeElement;
    }


    /**
      @return the MazeElement that the explorer is on.
              When the explorer's position is null, return WALL
              because the user-programmer's code is expected to benefit
              from that equivalence.
     */
    public int explorerIsOnA() {
        if( explorerPosition == null) return WALL;
        else return maze[ explorerPosition.rank][ explorerPosition.file];
    }


    /**
       a pair of rank & file that can represent...
         o  a displacement from the current location
         o  a location in a maze, being a displacement from (0,0)
       A location outside the maze is represented by a null Vector.
     */
    private class Vector {
        private int rank, file;
        
        // The no-arg constructor produces [0, 0] 
        private Vector() {}

        // copy-constructor
        private Vector( Vector old) {
            rank = old.rank;
            file = old.file;
        }

        /* For other rank and file values, use add so that the Vector
           will be null if the displacement exceeds the maze bounds.
           There is no constructor with rank and file arguments because
           a constructor cannot produce a null.
         */

        private Vector add( int ranks, int files) { 
            rank += ranks;
            file += files;
            
            // // for debugging: report resulting position
            // System.out.println( "sum: " + rank + " / " + rankCount
                              // + ", " +    file + " / " + maze[ rank].length );
            
            // still in bounds?
            if(    0 <= rank && rank < rankCount
                && 0 <= file && file < maze[ rank].length
              )  return this;
            else return null;  // outside maze
        }


        /**
          @return whether this Vector matches the parameters
         */
        private boolean equals( int rank, int file) {
            return this.rank == rank && this.file == file;
        }
    }
}
