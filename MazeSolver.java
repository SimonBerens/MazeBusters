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
        if (maze.explorerIsOnA() == Maze.Tile.TREASURE) {
            System.out.println(maze);
            return true;
        }
        if (maze.explorerIsOnA() != Maze.Tile.STEPPING_STONE) return false;

        // try every direction
        for (Maze.Direction direction:
        Maze.Direction.values()){

            // call the recursive abstraction after turning the current position
            // into a wall and moving in a direction
            if(solve(new Maze(maze).dropA(Maze.Tile.PATH).go(direction))) {
                return true;
            }
        }
        return false;
    }
}
