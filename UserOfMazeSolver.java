public class UserOfMazeSolver {

    static Displayer displayer;

    public static void main(String[] commandLine)
            throws java.io.FileNotFoundException {
        System.out.println();

        Maze maze = new Maze( commandLine[0]
                , Integer.parseInt( commandLine[1])
                , Integer.parseInt( commandLine[2])
        );
        System.out.println( maze + System.lineSeparator());
/**
        Displayer Test: works for invocations such as...
            - java UserOfMazeSolver mazes/fork_longWest.txt 0 5 0
*/
        // displayer = new Displayer( Integer.parseInt( commandLine[3]));
        // displayerTest( maze);

        System.out.println(MazeSolver.solve(maze));
    }

    private static void displayerTest( Maze m) {
        int step = 0;

        displayer.atTopOfWindow( m + "step " + step++);

        // move past west edge, Displaying as we go
        while( step < 5) {
            m.go( Maze.WEST);
            displayer.atTopOfWindow( m + "step " + step++);
        }
    }
}
