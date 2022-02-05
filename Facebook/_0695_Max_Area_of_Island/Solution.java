package Facebook._0695_Max_Area_of_Island;

import java.util.LinkedList;
import java.util.Queue;

/*
695. Max Area of Island
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
The area of an island is the number of cells with a value 1 in the island.
Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:
Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
*/
/*
Solution
Approach 1: bfs
*/
public class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    int s = 1;
                    queue.add(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] info = queue.poll();
                        int x = info[0];
                        int y = info[1];
                        res = Math.max(res, s);
                        if(x > 0 && grid[x - 1][y] != 0){
                            grid[x - 1][y] = 0;
                            queue.add(new int[]{x - 1, y});
                            s++;
                        }
                        if(x < grid.length - 1 && grid[x + 1][y] != 0){
                            grid[x + 1][y] = 0;
                            queue.add(new int[]{x + 1, y});
                            s++;
                        }
                        if(y < grid[0].length - 1 && grid[x][y + 1] != 0){
                            grid[x][y + 1] = 0;
                            queue.add(new int[]{x, y + 1});
                            s++;
                        }
                        if(y > 0 && grid[x][y - 1] != 0){
                            grid[x][y - 1] = 0;
                            queue.add(new int[]{x, y - 1});
                            s++;
                        }
                    }

                }
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
