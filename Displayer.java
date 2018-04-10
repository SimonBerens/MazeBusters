/**
  Display a string at the top of the window.
  To make successive displays overlap, set the window height
  to the height of your shell window.
  To make successive displays scroll, specify a window height of 0.
 */
public class Displayer {
    
    private int windowHeight;
    private java.io.Console console;

    /**
      Construct an instance that will display mazes in a window
      with height @windowHeight.
     */
    public Displayer( int windowHeight) {
        this.windowHeight = windowHeight;
        console = System.console();  /* see docs on Console for
            conditions under which console is non-null */
    }


    /**
      Display @pic at the top of the window.
     */
    public void atTopOfWindow( String pic) {
        System.out.print( pic);
        
        // fill rest of screen
        for( int linesPrinted = lineSeparatorsIn( pic)
           ; linesPrinted < windowHeight -1  // leave 1 for cursor
           ; linesPrinted++
           )
            System.out.print( System.lineSeparator());

        // wait until user has assimilated the display
        if( console != null)
            console.readLine( "<enter> to continue: ");
    }


    /**
      @return the number of lineSeparators in @string
      
      It would be pathetic for Java to lack a built-in
      substring-counter. But I've wasted too long looking for it.
     */
    private static int lineSeparatorsIn( String string) {
        int count = 0;
        int fromIndex = 0;
        while( ( fromIndex = string.indexOf( System.lineSeparator()
                                           , fromIndex)
               ) != -1
            ) {
            count++;
            fromIndex += System.lineSeparator().length();
            }
        return count;
    }
}
