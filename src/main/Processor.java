package main;

import menu.Menu;
import util.Constants;

public class Processor
{

    private String[][] board =
    {
            { Constants.BLANK, Constants.O, Constants.X },
            { Constants.O, Constants.X, Constants.O },
            { Constants.O, Constants.X, Constants.BLANK } };

    public static void main( String[] args )
    {
        Processor processor = new Processor();
        processor.run();

    }

    private void run()
    {
        String input;
        String[] parts;
        String currentPiece;
        int loop = 0;

        Menu.printMenu();
        startGame();

        String winner = null;
        while ( winner == null )
        {
            printBoard();
            currentPiece = Constants.pieces[loop % 2];
            input = Menu.getInput( currentPiece
                    + "'s turn! Enter the coordinate to place your piece. Row, Column (0,0 is top left corner)" );

            if ( !isCoordValid( input ) )
            {
                System.out.println( "Not a valid input! Try again" );
                continue;
            }

            parts = input.split( Constants.COMMA );
            int row = Integer.valueOf( parts[0].trim() );
            int col = Integer.valueOf( parts[1].trim() );

            if ( !isSquareOpen( row, col ) )
            {
                System.out.println( "Square is already taken! Try again" );
                continue;
            }

            placePiece( currentPiece, row, col );

            winner = detectWinner();
            if ( winner == null )
            {
                if ( isTie() )
                {
                    printBoard();
                    System.out.println( "Tie game!" );
                    break;
                } else
                {
                    System.out.println( "Next move" );
                }
            } else
            {
                printBoard();
                System.out.println( winner + "!" );
            }
            loop++;
        }
    }

    private void startGame()
    {
        System.out.println( "X to start the game!" );
        System.out.println( "Enter a coordinate to place your piece (eg 1,2 will be the first row, second column)" );
        resetBoard();
    }

    private void placePiece( String piece, int row, int col )
    {
        board[row][col] = piece;
    }

    private boolean isCoordValid( String coord )
    {
        if ( coord.trim().isEmpty() )
        {
            return false;
        }

        if ( !coord.contains( Constants.COMMA ) )
        {
            return false;
        }

        String[] parts = coord.split( Constants.COMMA );

        if ( parts.length != 2 )
        {
            return false;
        }

        try
        {
            Integer.valueOf( parts[0].trim() );
            Integer.valueOf( parts[1].trim() );
        } catch ( Exception e )
        {
            return false;
        }

        int row = Integer.valueOf( parts[0].trim() );
        int col = Integer.valueOf( parts[1].trim() );

        if ( row > board.length || col > board.length )
        {
            System.out.println( "Entered a coordinate thats off the board!" );
            return false;
        }

        if ( row < 0 || col < 0 )
        {
            System.out.println( "Entered a coordinate thats off the board!" );
            return false;
        }

        return true;
    }

    private boolean isSquareOpen( int row, int col )
    {
        if ( board[row][col].trim().isEmpty() )
        {
            return true;
        }

        return false;
    }

    private void resetBoard()
    {
        board = new String[][]
        {
                { Constants.BLANK, Constants.BLANK, Constants.BLANK },
                { Constants.BLANK, Constants.BLANK, Constants.BLANK },
                { Constants.BLANK, Constants.BLANK, Constants.BLANK } };
    }

    private void printBoard()
    {
        System.out.println( Constants.BORDER );

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board.length; col++)
            {
                System.out.print( board[row][col] );
                if ( col != board.length - 1 )
                {
                    System.out.print( Constants.PIPE );
                }
            }
            if ( row != board.length - 1 )
            {
                System.out.println( Constants.LINE );
            }

        }

        System.out.println();
        System.out.println( Constants.BORDER );
    }

    private String detectWinner()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board.length; col++)
            {
                if ( board[row][col].trim().isEmpty() )
                {
                    continue; // no winner in this row, column, or diagonal
                }

                String potentialWinner = board[row][col];

                // test rows and columns first
                if ( col == 0 )
                {
                    if ( testRow( row, potentialWinner ) )
                    {
                        return potentialWinner + " won on row " + ( row + 1 );
                    }
                }

                if ( row == 0 )
                {
                    if ( testCol( col, potentialWinner ) )
                    {
                        return potentialWinner + " won on column " + ( col + 1 );
                    }
                }

                // test diagonals
                if ( col == 0 )
                {
                    if ( row == 0 )
                    {
                        if ( testTopLeftDiagonal( potentialWinner ) )
                        {
                            return potentialWinner + " won on top left diagonal";
                        }
                    }
                    if ( row == board.length - 1 )
                    {
                        if ( testBottomLeftDiagonal( potentialWinner ) )
                        {
                            return potentialWinner + " won on bottom left diagonal";
                        }
                    }

                }

            }
        }

        return null;
    }

    private boolean testRow( int row, String strToTest )
    {
        for (int i = 0; i < board.length; i++)
        {
            if ( !strToTest.equalsIgnoreCase( board[row][i] ) )
            {
                return false;
            }
        }
        return true;

    }

    private boolean testCol( int col, String strToTest )
    {
        for (int i = 0; i < board.length; i++)
        {
            if ( !strToTest.equalsIgnoreCase( board[i][col] ) )
            {
                return false;
            }
        }
        return true;

    }

    private boolean testTopLeftDiagonal( String strToTest )
    {
        for (int i = 0; i < board.length; i++)
        {
            if ( !strToTest.equalsIgnoreCase( board[i][i] ) )
            {
                return false;
            }
        }
        return true;
    }

    private boolean testBottomLeftDiagonal( String strToTest )
    {
        int col = 0;
        for (int row = board.length - 1; row > 0; row--)
        {
            if ( !strToTest.equalsIgnoreCase( board[row][col] ) )
            {
                return false;
            }
            col++;
        }
        return true;
    }

    private boolean isTie()
    {

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board.length; col++)
            {
                if ( board[row][col].trim().isEmpty() )
                {
                    return false;
                }
            }
        }

        return true;
    }

}
