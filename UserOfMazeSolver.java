public class UserOfMazeSolver {

    static Displayer displayer;

    public static void main(String[] commandLine)
            throws java.io.FileNotFoundException {
        // TODO: 4/12/2018 add more mazes into /mazes (see https://piazza.com/class/j7oyiev6r7x576?cid=446) URGENT
        String[] mazes = {"mazes\\4cell_treasureWest.txt",
                        "mazes\\fork_longWest.txt",
                        "mazes\\intersection_noTreasure.txt",
                        "mazes\\intersection_treasureNorth.txt",
                        "mazes\\lotsOfTreasure.txt",
                        };
        for(int i = 0; i < mazes.length; i++) {
            System.out.println(mazes[i]);
            Maze maze = new Maze( mazes[i]
                , Integer.parseInt( commandLine[0])
                , Integer.parseInt( commandLine[1])
        ); 
        // TODO: 4/12/2018 for each maze, test it by creating a few random locations URGENT
        // TODO: 4/12/2018 don't use commandline args for this URGENT
            // Without easy access to the length/width of a maze, random locations are harder to implement
            // than would justify using them over command line estimations. Maybe a future version?

        System.out.println(MazeSolver.solve(maze));
        }
    }


    
}
