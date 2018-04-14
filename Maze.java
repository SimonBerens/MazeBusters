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

    /**
     * Possible Maze Tiles
     */
    public enum Tile {
        TREASURE,
        WALL,
        STEPPING_STONE,
        PATH,
    }

    /**
     * Possible movement directions
     */
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    private Tile[][] maze;
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
        maze = new Tile[ MAX_RANKS][];

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
            
            maze[ rank] = new Tile[ line.length()];

            // Convert the input line into maze elements.
            for( int file = 0; file < line.length(); file++ ) {
                String inChar = line.substring( file, file+1);
                Tile element;  // value destined for maze array
                if(      inChar.equals("0"))  element = Tile.TREASURE;
                else if( inChar.equals("*"))  element = Tile.STEPPING_STONE;
                // spaces and unrecognised characters are walls
                else                          element = Tile.WALL;
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
        maze = new Tile[rankCount][]; // *hopefully* works for non rectangular mazes
        for (int rank = 0; rank < rankCount; rank++) {
            int fileLength = old.maze[rank].length;
            maze[rank] = new Tile[fileLength];
            for (int file = 0; file < fileLength; file++) {
                maze[rank][file] = old.maze[rank][file];
            }
        }
    }


  


    /**
      @return a string representing of this instance
     */
    public String toString() {

        String output = "";
        for (int rank = 0; rank < rankCount; rank++) {

            for ( int file = 0; file < maze[ rank].length; file++) {
                 boolean explorerIsHere = explorerPosition != null && explorerPosition.equals( new Vector().add( rank, file));
                 // not as nice but much easier to understand
                 switch ( maze[ rank][ file]) {
                     case TREASURE:
                        output += explorerIsHere? "!" : "0";
                        break;
                     case WALL:
                        output += explorerIsHere? "E" : " ";
                        break;
                     case STEPPING_STONE:
                        output += explorerIsHere? "e" : "*";
                        break;
                     case PATH:
                        output += explorerIsHere? "E" : "#";
                 }
                if ( file == maze[ rank].length - 1) output += "\n";
            }
        }
        return output;
    }

    /**
      Move the Explorer a step in the indicated direction.
      Attempting to position the explorer outside the maze means
      it has no position.
      
      @precondition: explorer starts in a valid position
     */
    public Maze go( Direction direction)  {
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
        return this;
    }


    /**
      Modify the maze to have @mazeElement in the explorer's position.
      Nix dropping treasure.
     */
    public Maze dropA( Tile mazeElement) {
        if( mazeElement != Tile.TREASURE)
            maze[ explorerPosition.rank][ explorerPosition.file] = mazeElement;
        return this;
    }


    /**
      @return the MazeElement that the explorer is on.
              When the explorer's position is null, return WALL
              because the user-programmer's code is expected to benefit
              from that equivalence.
     */
    public Tile explorerIsOnA() {
        if( explorerPosition == null) return Tile.WALL;
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

        @Override
        public String toString() {
            return "[ " + rank + ", " + file + "]";
        }

        /**
         * Checks for equal ranks and files
         * @param obj Object to check for equality
         * @return their equality
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vector) return (rank == ((Vector) obj).rank) && file == ((Vector) obj).file;
            return false;
        }

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
