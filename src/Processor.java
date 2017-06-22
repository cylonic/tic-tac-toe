
public class Processor
{

    private String[][] board =
    {
            { "X", "O", "" },
            { "X", "X", "O" },
            { "X", "X", "" } };

    public static void main( String[] args )
    {
        Processor processor = new Processor();
        processor.printBoard();

        String msg = processor.detectWinner();
        if ( msg == null )
        {
            System.out.println( "Next move" );
        } else
        {
            System.out.println( msg + "!" );
        }

    }

    private void resetBoard()
    {
        board = new String[][]
        {
                { " ", " ", " " },
                { " ", " ", " " },
                { " ", " ", " " } };
    }

    private void printBoard()
    {
        System.out.print( board[0][0] );
        System.out.print( " | " );
        System.out.print( board[0][1] );
        System.out.print( " | " );
        System.out.print( board[0][2] );

        System.out.println( "\n---------" );

        System.out.print( board[1][0] );
        System.out.print( " | " );
        System.out.print( board[1][1] );
        System.out.print( " | " );
        System.out.print( board[1][2] );

        System.out.println( "\n---------" );

        System.out.print( board[2][0] );
        System.out.print( " | " );
        System.out.print( board[2][1] );
        System.out.print( " | " );
        System.out.print( board[2][2] );

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

}
