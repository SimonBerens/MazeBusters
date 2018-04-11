public class UserOfMazeSolver {
    public static void main(String[] commandLine)
            throws java.io.FileNotFoundException {
        System.out.println();

        Maze maze = new Maze( commandLine[0]
                , Integer.parseInt( commandLine[1])
                , Integer.parseInt( commandLine[2])
        );
        System.out.println( maze + System.lineSeparator());

        System.out.println(MazeSolver.solve(maze));
    }
}
