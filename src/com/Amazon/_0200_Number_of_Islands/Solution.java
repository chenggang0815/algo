package com.Amazon._0200_Number_of_Islands;
/*
200. Number of Islands

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ['1','1','1','1','0'],
  ['1','1','0','1','0'],
  ['1','1','0','0','0'],
  ['0','0','0','0','0']
]
Output: 1

Example 2:
Input: grid = [
  ['1','1','0','0','0'],
  ['1','1','0','0','0'],
  ['0','0','1','0','0'],
  ['0','0','0','1','1']
]
Output: 3

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.

*/
/*
solution:
// dfs
// bfs
*/
public class Solution {

    static int numberOfIslands(char[][] matrix){
        int res = 0;
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (matrix[i][j] == '1'){
                    dfs(matrix, i, j);
                    res++;
                }
            }
        }
        
        return res;
    }
    
    static void dfs(char[][] matrix, int i, int j){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] != '1'){
            return;
        }

        matrix[i][j] = '#';
        dfs(matrix, i - 1, j);
        dfs(matrix, i + 1, j);
        dfs(matrix, i, j - 1);
        dfs(matrix, i, j + 1);
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        System.out.println(numberOfIslands(grid));
    }
}
