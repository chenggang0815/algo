package LeetCode._0200_Number_of_Islands;
/*
200. Number of Islands

Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

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
 */

import java.util.LinkedList;
import java.util.Queue;

/*
1. dfs - 不改变原始数组grid
因为本题直接dfs遍历寻找符合条件的解比较困难，所以需要对每个可能符合条件的岛屿进行dfs遍历，把当前岛屿内的1都标记，只需要计算有多少次dfs遍历就行了

不改变原始数组grid，每次遍历修改flag[i][j] time：o(m*n) space:o(m*n)
1.1 如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。在深度优先搜索的过程中，每个搜索到的1都会被重新标记为0
1.2 最终岛屿的数量就是我们进行深度优先搜索的次数


2. dfs - 改变原始数组grid，每次遍历直接修改grid  time：o(m*n) space:o(m*n)

3. bfs
 */
public class Solution {
    static void dfs(char[][] grid, int i, int j, int[][] flag){
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == '0' || flag[i][j] == 1){
            return;
        }
        flag[i][j] = 1;
        dfs(grid, i - 1, j, flag);
        dfs(grid, i + 1, j , flag);
        dfs(grid, i, j - 1, flag);
        dfs(grid, i, j + 1, flag);
        /*
        错误版本
        dfs(grid, i + 1, j , flag);
        dfs(grid, i, j + 1, flag);
        对于每个数字，不能只遍历右边和下边的数字，还需要遍历左和上才能连贯
        例如对如下矩阵,不向左遍历不行
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        例如对如下矩阵,不向上遍历不行
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
         */
    }
    // dfs
    static int numIslands1(char[][] grid) {
        int[][] flag = new int[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1' && flag[i][j] == 0){
                    dfs(grid, i, j, flag);
                    res++;
                }
            }
        }

        return res;
    }

    // bfs https://leetcode-cn.com/problems/number-of-islands/solution/number-of-islands-shen-du-you-xian-bian-li-dfs-or-/
    static int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    num_islands++;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }


        return num_islands;
    }


    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'0','1','0','0','0'},
                {'1','1','0','1','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        char[][] grid1 = new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        };

        System.out.println(numIslands2(grid));

    }
}
