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
1. iterate the matrix, if current position grid[i][j] == '1', put the current position in queue, res++
2. do bfs base the position in the queue
3. why we need to put the grid[x - 1][y] = '0' in the if condition, not the 104 line
   because it will cause duplicate calculation
for example:
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
if we change the value to '0'  before the if , the order to queue is
first while [0,0] check current position => [1,0] [0,1]
second while [1,0] check current position => [1,1]
              [0,1] check current position => [1,1]
third while, we have two [1,1] [1,1]

if we change the position before put it into the queue
first while [0,0]  => [1,0] [0,1] => matrix[1,0]=0 matrix[0,1]=0
second while [1,0] => [1,1] => matrix[1,1]=0
              [0,1] no position meet condition
third while, we have one [1,1]
 */
public class Solution {
    void dfs2(int i, int j, char[][] grid){
        grid[i][j] = '0';
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] direction : directions){
            int x = i + direction[0];
            int y = j + direction[1];
            if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) continue;
            if(grid[x][y] == '0') continue;

            dfs2(x, y, grid);
        }
    }

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

    // bfs
    static int numIslands2(char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;
                    grid[i][j] = '0';
                    queue.add(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                       // grid[x][y] = '0';
                        if(x > 0 && grid[x - 1][y] == '1' ){
                            queue.add(new int[]{x - 1, y});
                            grid[x - 1][y] = '0'; // 不加这一行过不了
                        }
                        if(x < m - 1 && grid[x + 1][y] == '1' ){
                            queue.add(new int[]{x + 1, y});
                            grid[x + 1][y] = '0';
                        }
                        if(y > 0 && grid[x][y - 1] == '1' ){
                            queue.add(new int[]{x, y - 1});
                            grid[x][y - 1] = '0';
                        }
                        if(y < n - 1 && grid[x][y + 1] == '1' ){
                            queue.add(new int[]{x, y + 1});
                            grid[x][y + 1] = '0';
                        }
                    }
                }
            }
        }

        return res;
    }

    public int numIslands3(char[][] grid) {
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    queue.add(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] point = queue.poll();
                        //grid[point[0]][point[1]] = '0'; //可以不用这一行，因为不会再重复遍历这个点了
                        for(int[] direction : directions){
                            int x = point[0] + direction[0];
                            int y = point[1] + direction[1];
                            if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) continue;
                            if(grid[x][y] == '0') continue;
                            queue.add(new int[]{x, y});
                            grid[x][y] = '0'; // 不加这行会超时，可能是把重复的点加到queue里面了
                        }
                    }
                    res++;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
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
