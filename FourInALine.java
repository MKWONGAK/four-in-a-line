import java.util.Scanner;

public class FourInALine{
    static int [][] board = new int[6][7];
    static int row = 6, col = 7, counter = 0, input;
    static int [] colFill = new int[7];

    public static void PrintBoard(){
        for(int i=row-1; i>=0; --i){
            for(int j=0; j<col; ++j){
                if(j==0){
                    System.out.print(i + " :");
                }
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
        System.out.println("    -------------");
        System.out.print("   ");
        for(int j=0; j<col; ++j){
            System.out.print(" " + j);
        }
        System.out.println();
    }

    public static boolean checkWin(int Player){
        boolean checker = false;

        //check vertical
        for(int i=0; i<col; ++i){
            int countVertical = 0;
            for(int j=0; (j<colFill[i])&&(colFill[i]>=4); ++j){
                if(board[j][i]==Player){
                    ++countVertical;
                    if(countVertical==4){
                        checker = true;
                        return checker;
                    }
                }else{
                    countVertical = 0;
                }
            }
        }

        //check horizontal
        for(int i=0; i<row; ++i){
            int countHorizontal = 0;
            for(int j=0; j<col; ++j){
                if(board[i][j]==Player){
                    ++countHorizontal;
                    if(countHorizontal==4){
                        checker = true;
                        return checker;
                    }
                }else{
                    countHorizontal = 0;
                }
            }
        }

        //check to right diagonal
        //i,j for initial position; n,m for the right diagonal start from i,j 
        for(int i=0; i<row-3; ++i){
            for(int j=0; j<col-3; ++j){
                int countDiagonal = 0;
                for(int n=i, m=j; (n<row)&&(m<col); ++n, ++m){
                    if(board[n][m]==Player){
                        ++countDiagonal;
                        if(countDiagonal==4){
                            checker = true;
                            return checker;
                        }
                    }else{
                        countDiagonal = 0;
                    }
                }
            }
        }

        //check to left diagonal
        for(int i=0; i<row-3; ++i){
            for(int j=col-1; j>=3; --j){
                int countDiagonal = 0;
                for(int n=i, m=j; (n<row)&&(m>=0); ++n, --m){
                    if(board[n][m]==Player){
                        ++countDiagonal;
                        if(countDiagonal==4){
                            checker = true;
                            return checker;
                        }
                    }else{
                        countDiagonal = 0;
                    }
                }
            }
        }

        return checker;
    }



    public static void main(String [] args){
        Scanner kb = new Scanner(System.in);
        while(true){
            //construct the board and response
            PrintBoard();
            if(counter%2 == 0){
                System.out.print("Player 1 type a column (0-6) or 9 to quit current game: ");
            }else{
                System.out.print("Player 2 type a column (0-6) or 9 to quit current game: ");
            }
            input = kb.nextInt();
            if(input == 9){
                System.out.println("Game quited!");
                break;
            }
            if((input>=7)||(input<0)){
                System.out.println("Range of column should be 0 to 6!");
                continue;
            }
            if(colFill[input]==6){
                System.out.println("Column " + input + " is full!");
                continue;
            }

            //fill the board
            if(counter%2 == 0){
                board[colFill[input]][input] = 1;
            }else{
                board[colFill[input]][input] = 2;
            }
            ++colFill[input];
            
            //check winning
            if(checkWin(counter%2 + 1)){
                System.out.println("=========================================================");
                PrintBoard();
                System.out.println("Player " + (counter%2 + 1) + " win this game!");
                break;
            }

            //Tie
            if((counter+1) == row * col){
                System.out.println("=========================================================");
                PrintBoard();
                System.out.println("Tie!");
                break;
            }

            ++counter;
            System.out.println();
        }
        System.out.println("Total steps = " + (counter + 1));
        kb.close();
    }
}