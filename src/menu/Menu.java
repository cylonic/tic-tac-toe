package menu;

import java.util.Scanner;

public class Menu
{

    private static Scanner in = new Scanner( System.in );

    public static void printMenu()
    {
        System.out.println( "*** Welcome! ***" );
        System.out.println();
        System.out.println( "Tic Tac Toe. First to get 3 in a row wins!" );
        System.out.println();

    }

    public static String getInput( String msg )
    {
        System.out.println( msg );
        return in.nextLine().toUpperCase().trim();
    }

}
