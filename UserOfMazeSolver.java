public class UserOfMazeSolver {

    static Displayer displayer;

    public static void main(String[] commandLine)
            throws java.io.FileNotFoundException {
        System.out.println();

        // TODO: 4/12/2018 add more mazes into /mazes (see https://piazza.com/class/j7oyiev6r7x576?cid=446) URGENT

        Maze maze = new Maze( commandLine[0]
                , Integer.parseInt( commandLine[1])
                , Integer.parseInt( commandLine[2])
        ); // TODO: 4/12/2018 replace this with an array of the file names, iterate through the array URGENT
        // TODO: 4/12/2018 for each maze, test it by creating a few random locations URGENT
        // TODO: 4/12/2018 use the displayer on each one URGENT
        // TODO: 4/12/2018 don't use commandline args for this URGENT
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

    // TODO: 4/12/2018 we don't need the displayer test, jsut to use the displayer URGENT
    
}
