package LeetCode._0052_N_Queens_II;
/*
52. N-Queens II

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.
*/

/*
Solution:
similar to leetcode 51
*/
public class Solution {
    public int totalNQueens(int n) {
        int[] res = new int[]{0};
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = '.';
            }
        }
        dfs(matrix, 0, res);

        return res[0];
    }

    static void dfs(char[][] matrix, int row, int[] res){
        // reach solution
        if (row == matrix.length){
            res[0]++;
            return;
        }

        for (int i = 0; i < matrix.length; i++){
            matrix[row][i] = 'Q';
            if (check(matrix, row, i)){
                dfs(matrix, row + 1, res);
            }
            matrix[row][i] = '.';
        }
    }

    static boolean check(char[][] matrix, int row, int col){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < matrix.length; j++){
                //if (i == row && j == col) continue;
                if (matrix[i][j] == 'Q' && (j == col || Math.abs(row - i) == Math.abs(col - j))) return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {

    }
}
