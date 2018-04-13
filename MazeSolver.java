/** 
    Maze solver class with method solve that takes a Maze as an argument
    Returns the boolean value of the statement "This maze has a solution"
*/

public class MazeSolver {

    static Displayer displayer = new Displayer(1);

    public static boolean solve(Maze maze) {
        displayer.atTopOfWindow(maze.toString());
        if (maze.explorerIsOnA() == Maze.TREASURE) return true; // TODO: 4/11/2018 make @explorerIsOnA take a parameter
                                                                        // ??
        if (maze.explorerIsOnA() != Maze.STEPPING_STONE) return false;
        // TODO: 4/12/2018 add check for if explorer is outside of maze (explorer == null) URGENT
            // ok but like why don't we just keep calling it a wall
        // TODO: 4/11/2018 make go return a maze
        // TODO: 4/11/2018 make dropA return a maze 
        Maze northMaze = new Maze(maze);
        Maze southMaze = new Maze(maze);
        Maze eastMaze = new Maze(maze);
        Maze westMaze = new Maze(maze);
        Maze[] mazes = {northMaze, southMaze, eastMaze, westMaze,};
        int[] directions = {Maze.NORTH, Maze.SOUTH, Maze.EAST, Maze.WEST,};
        for(int i = 0; i < 4; i++) {
            mazes[i].dropA(Maze.WALL);
            mazes[i].go(directions[i]);
        }
        return solve(northMaze) || solve(southMaze) || solve(eastMaze) || solve(westMaze);
    }
}
