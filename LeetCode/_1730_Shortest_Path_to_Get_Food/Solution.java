package LeetCode._1730_Shortest_Path_to_Get_Food;
/*
1730. Shortest Path to Get Food
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
You are given an m x n character matrix, grid, of these different types of cells:
1. '*' is your location. There is exactly one '*' cell.
2. '#' is a food cell. There may be multiple food cells.
3. 'O' is free space, and you can travel through these cells.
4. 'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

Example 1:
Input: grid = [["X","X","X","X","X","X"],
               ["X","*","O","O","O","X"],
               ["X","O","O","#","O","X"],
               ["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.
*/

import java.util.LinkedList;
import java.util.Queue;

/*
similar to leetcode 130
Solution
Approach 1: BFS time: O(m*n) space: O(m*n)
1. start bfs from "*", add  (i, j, step) into queue
2. do a bfs step by step and res++

why Can't we apply DFS here? Can somebody explain! Thanks
Technically, you can apply DFS too. You have to apply DFS & store the shortest path separately.
But BFS does that for you by its very nature of traversal.
You can visualise BFS traversal like when you drop a stone in a pond, the water spreads out in radial direction, you would reach the points in shortest distance.
*/
public class Solution {
    // version 1
    public int getFood(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] start = new int[3];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == '*'){
                    start[0] = i;
                    start[1] = j;
                    start[2] = 0;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            int step = current[2];

            if(j < col - 1 && grid[i][j + 1] != 'X'){
                if(grid[i][j + 1] == '#') {
                    return step + 1;
                }else{
                    grid[i][j + 1] = 'X';
                    int[] index = new int[3];
                    index[0] = i;
                    index[1] = j + 1;
                    index[2] = step + 1;
                    queue.add(index);
                }
            }

            if(j > 0 && grid[i][j - 1] != 'X'){
                if(grid[i][j - 1] == '#'){
                    return step + 1;
                }else{
                    grid[i][j - 1] = 'X';
                    int[] index = new int[3];
                    index[0] = i;
                    index[1] = j - 1;
                    index[2] = step + 1;
                    queue.add(index);
                }
            }

            if(i < row - 1 && grid[i + 1][j] != 'X'){
                if(grid[i + 1][j] == '#'){
                    return step + 1;
                }else{
                    grid[i + 1][j] = 'X';
                    int[] index = new int[3];
                    index[0] = i + 1;
                    index[1] = j;
                    index[2] = step + 1;
                    queue.add(index);
                }
            }

            if(i > 0 && grid[i- 1][j] != 'X'){
                if(grid[i - 1][j] == '#'){
                    return step + 1;
                }else{
                    grid[i - 1][j] = 'X';
                    int[] index = new int[3];
                    index[0] = i - 1;
                    index[1] = j;
                    index[2] = step + 1;
                    queue.add(index);
                }
            }
        }

        return -1;
    }

    public int getFood2(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] start = new int[3];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '*'){
                    start = new int[]{i, j, 0};
                    break;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] directions= new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1,0}};
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            int step = current[2] + 1;

            for(int[] direction: directions){
                int row = i + direction[0];
                int col = j + direction[1];
                if(row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 'X') continue;
                if(grid[row][col] == '#') return step;
                grid[row][col] = 'X';
                int[] index = new int[]{row, col, step};
                queue.add(index);
            }
        }

        return -1;
    }

    public int getFood3(char[][] grid) {
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '*'){
                    queue.add(new int[]{i,j});
                    break;
                }
            }
        }

        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        while(!queue.isEmpty()){
            int cnt = queue.size();
            res++;
            while(cnt > 0){
                int[] cur = queue.poll();
                for(int[] direction: directions){
                    int x = cur[0] + direction[0];
                    int y = cur[1] + direction[1];
                    if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 'X') continue;
                    if(grid[x][y] == '#') return res;
                    grid[x][y] = 'X';
                    queue.add(new int[]{x, y});
                }
                cnt--;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3};
        nums = new int[]{1, 2, 3};
        int[] n;
        n = new int[]{1,2,3};
    }
}
