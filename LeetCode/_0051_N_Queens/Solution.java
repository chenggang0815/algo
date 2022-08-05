package LeetCode._0051_N_Queens;
/*
51. N-Queens
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
*/

import java.util.ArrayList;
import java.util.List;

/*
Solution: Recursion time: O(N!) space:O(N^2)
https://leetcode-cn.com/problems/n-queens/solution/gen-ju-di-46-ti-quan-pai-lie-de-hui-su-suan-fa-si-/
https://www.youtube.com/watch?v=xouin83ebxE&t=782s
*/
public class Solution {
    static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;

        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = '.';
            }
        }
        dfs(matrix, 0, res);

        return res;
    }

    static void dfs(char[][] matrix, int row, List<List<String>> res){
        // reach solution
        if (row == matrix.length){
            res.add(builder(matrix));
            return;
        }

        for (int i = 0; i < matrix.length; i++){
            matrix[row][i] = 'Q';
            if (check(matrix, row, i)){
                dfs(matrix, row + 1, res); // place the 'Q' row by row
            }
            matrix[row][i] = '.';
        }
    }

    static boolean check(char[][] matrix, int row, int col){
        for (int i = 0; i < row; i++){ // for (int i = 0; i < row; i++){ only check rows above current one
            for (int j = 0; j < matrix.length; j++){
               // if (i == row && j == col) continue;
                // not need to check current position
                // actually, It will not move to the current position, because the i < row, current position is [row, col]
                if (matrix[i][j] == 'Q' && (j == col || Math.abs(row - i) == Math.abs(col - j))) return false;
                // if on the diagonal line => (x1,y1) (x2,y2) => Math.abs(y2 - y1) / Math.abs(x2 - x1) = 1
                // if matrix[i][j] == 'Q' and j == col, which means there is another 'Q' on the same column
                // because we place the 'Q' row by row, so it can guarantee on 'Q' on the same row
                // 由于是一行一行考虑放置皇后，摆放的这些皇后肯定不在同一行
            }
        }

        return true;
    }

    static List<String> builder(char[][] matrix){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < matrix.length; j++){
                sb.append(matrix[i][j]);
            }
            res.add(sb.toString());
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
