/** 
    Maze solver class with method solve that takes a Maze as an argument
    Returns the boolean value of the statement "This maze has a solution"
*/

public class MazeSolver {

    /**
     * Finds out if the maze is solvable by trying to move in
     * all directions if the explorer is on a valid Tile
     * @param maze the maze to solve
     * @return the solvability of the maze
     */

    public static boolean solve(Maze maze) {

        // base cases
        if (maze.explorerIsOnA() == Maze.Tile.TREASURE) return true;
        if (maze.explorerIsOnA() != Maze.Tile.STEPPING_STONE) return false;

        // save the maze so you can backtrack
        Maze snapshot = new Maze(maze);

        // try every direction
        for (Maze.Direction direction:
        Maze.Direction.values()){

            // call the recursive abstraction after turning the current position
            // into a wall and moving in a direction
            if(solve(maze.dropA(Maze.Tile.WALL).go(direction))) return true;

            // set the current maze back to the snapshot
            maze = new Maze(snapshot);
        }
        return false;
    }
}
