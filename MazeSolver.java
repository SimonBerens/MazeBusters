/** 
    Maze solver class with method solve that takes a Maze as an argument
    Returns the boolean value of the statement "This maze has a solution"
*/

public class MazeSolver {
    public static boolean solve(Maze maze) {
        if (maze.explorerIsOnA() == Maze.TREASURE) return true; // TODO: 4/11/2018 make @explorerIsOnA take a parameter
        if (maze.explorerIsOnA() == Maze.WALL) return false;
        // TODO: 4/12/2018 replace with check with if explorer is on anything other than a wall (to handle irregular characters) URGENT
        // TODO: 4/12/2018 add check for if explorer is outside of maze (explorer == null) URGENT
        // TODO: 4/11/2018 make go return a maze
        // TODO: 4/11/2018 make dropA return a maze 
        Maze northMaze = new Maze(maze);
        northMaze.dropA(Maze.WALL);
        northMaze.go(Maze.NORTH);
        Maze southMaze = new Maze(maze);
        southMaze.dropA(Maze.WALL);
        southMaze.go(Maze.SOUTH);
        Maze eastMaze = new Maze(maze);
        eastMaze.dropA(Maze.WALL);
        eastMaze.go(Maze.EAST);
        Maze westMaze = new Maze(maze);
        westMaze.dropA(Maze.WALL);
        westMaze.go(Maze.WEST);
        // TODO: 4/12/2018 refactor into a for loop URGENT (array of directions, create a new maze each iteration)
        return solve(northMaze) || solve(southMaze) || solve(eastMaze) || solve(westMaze);
    }
}
