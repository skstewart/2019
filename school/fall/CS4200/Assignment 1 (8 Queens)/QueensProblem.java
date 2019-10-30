/**
 * Shayna Stewart
 * CS4200
 * Assignment 1
 * 8 Queens Problem
 */
public class QueensProblem {

    int N = 8;

    boolean solve(int board[][], int col) {

        if (col >= N) {
            return true;
        }

        for (int i = 0; i < N; i++) {
            if (safe(board, i, col)) {

                board[i][col] = 1;

                if (solve(board, col + 1) == true) {
                    return true;
                }

                board[i][col] = 0;
            }
        }

        return false;
    }

    boolean safe(int board[][], int row, int col) { //finds out whether a queen is safe (no other queens in its column/row, and no queens in diagonal
        int i, j;

        for (i = 0; i < col; i++) { //checking the column for queens
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) { //checking the diagonal for queens
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (i = row, j = col; j >= 0 && i < N; i++, j--) { //checking the other diagonal for queens
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    void print(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    System.out.print("  " + board[i][j] + "  ");
                } else {
                    System.out.print("  Q" + i + " "); //if the value is a 1 (meaning, a queen is there) then print a Q with its number
                }
            }

            System.out.print("\n\n"); //for formatting 
        }
    }

    public static void main(String args[]) {
        QueensProblem Queen = new QueensProblem();
        int board[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};

        Queen.solve(board, 0);

        Queen.print(board);
    }
}
