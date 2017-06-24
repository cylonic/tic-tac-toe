import util.Constants;

public class Processor
{

    private String[][] board =
    {
            { Constants.X, Constants.O, Constants.X },
            { Constants.O, Constants.X, Constants.O },
            { Constants.O, Constants.X, Constants.O } };

    public static void main( String[] args )
    {
        Processor processor = new Processor();
        processor.printBoard();

        String msg = processor.detectWinner();
        if ( msg == null )
        {
            if ( processor.isTie() )
            {
                System.out.println( "Tie game!" );
            } else
            {
                System.out.println( "Next move" );
            }
        } else
        {
            System.out.println( msg + "!" );
        }

        processor.resetBoard();
        processor.printBoard();

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
    }

    private String detectWinner()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board.length; col++)
            {
                if ( board[row][col] == "" )
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
