package LeetCode._0037_Sudoku_Solver;

/*
37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:
1. Each of the digits 1-9 must occur exactly once in each row.
2. Each of the digits 1-9 must occur exactly once in each column.
3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

*/
/*
solution: Backtracking time:O(9^(m*n))

            col=0,1,2 col=3,4,5  col=6,7,8
row=0,1,2    x1         x2       x3

row=3,4,5    x4         x5       x6

row=6,7,8    x7         x8       x9

row = 5 col = 4
block id = [(5/3)*3, (4/3)*3] => [3,3] => x5

*/
public class Solution {
    void solveSudoku(char[][] board){
        solve(board);
    }

    boolean solve(char[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == '.'){
                    for (char num = 1; num <= 9; num++){
                        if(isValid(board, i, j, num)){
                            board[i][j] = num;
                            if (solve(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    boolean isValid(char[][] board, int row, int col, int num){
        int blockCol = (col / 3) * 3;
        int blockRow = (row / 3) * 3;
        for (int i = 0; i < 9; i++){
            if (board[i][col] == num) return false;
            if (board[row][i] == num) return false;
            if (board[blockRow + i / 3][blockCol + i % 3] == num) return false;
        }

        return true;
    }
    public static void main(String[] args) {

    }
}
