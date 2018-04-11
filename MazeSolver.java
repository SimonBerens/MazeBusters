public class MazeSolver {
    public static boolean solve(Maze maze) {
        if (maze.explorerIsOnA() == Maze.TREASURE) return true; // TODO: 4/11/2018 make @explorerIsOnA take a parameter
        if (maze.explorerIsOnA() == Maze.WALL) return false;
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
        return solve(northMaze) || solve(southMaze) || solve(eastMaze) || solve(westMaze);
    }
}