import java.io.*;
import java.util.Scanner;

public class UserOfMazeSolver {

    public static void main(String[] commandLine) throws FileNotFoundException {

        // see https://piazza.com/class/j7oyiev6r7x576?cid=446
        String filePath = "C:\\Users\\gbere\\Desktop\\AP_CS\\MazeSolver\\mazes"; // modify as necessary
        String[] mazes = ( new File( filePath)).list();

        int option;
        do {
            showOptions( mazes);

            System.out.println( "Please select an option");
            test( validateInput( mazes.length), mazes);

            System.out.println( "Please select what you would like to do next \n" +
                    "0: Exit \n" +
                    "1: Test another maze \n");
            option = validateInput( 2);

        } while (option != 0);
    }

    private static void test(int choice, String[] mazes) throws FileNotFoundException {
        System.out.println( "Now solving " + mazes[ choice]);

        String fileName = "mazes\\" + mazes[ choice];

        // show the maze to solve
        try ( BufferedReader br = new BufferedReader( new FileReader( fileName))) {
            String line;
            while ( ( line = br.readLine()) != null) {
                System.out.println( line);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }

        // select starting position
        Scanner sc = new Scanner( System.in);
        System.out.println( "Where would you like to start?");
        System.out.println( "rank:");
        int rank = sc.nextInt();
        sc.nextLine();
        System.out.println( "file:");
        int file = sc.nextInt();

        // create and solve maze
        Maze maze = new Maze( fileName, rank, file);
        System.out.println( maze);
        System.out.println( MazeSolver.solve( maze));
    }

    /**
     * Makes sure the user selects one of the mazes
     * @param range the range of values [ 0, range) that the function will accept
     * @return the user's choice
     */

    private static int validateInput( int range) {
        Scanner sc = new Scanner(System.in);

        int questionableChoice;
        do {
            System.out.println( "Please enter a valid input");
            questionableChoice = sc.nextInt();
            sc.nextLine();
        } while (!(questionableChoice >= 0 && questionableChoice < range));
        return questionableChoice; // no longer questionable
    }

    /**
     * Displays the names of possible mazes to test
     * @param mazes the array of mazes
     */

    private static void showOptions( String[] mazes) {
        System.out.println( "Here are your options");

        for ( int i = 0; i < mazes.length; i++) {
            System.out.println( i + ": " + mazes[ i]);
        }
    }

}
