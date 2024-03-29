package LeetCode._1905_Count_Sub_Islands;

import java.util.LinkedList;
import java.util.Queue;

/*
1905. Count Sub Islands

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land).
An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
Return the number of islands in grid2 that are considered sub-islands.

Example 1:
Input: grid1 = [[1,1,1,0,0],
                [0,1,1,1,1],
                [0,0,0,0,0],
                [1,0,0,0,0],
                [1,1,0,1,1]],
       grid2 = [[1,1,1,0,0],
                [0,0,1,1,1],
                [0,1,0,0,0],
                [1,0,1,1,0],
                [0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
*/
/*
Solution: refer to 200. Number of Islands
Approach 1: dfs
1. iterate the grid2, if current value is 1, do a dfs, use a variable flag to check if meet all the '1' is in the grid1
2. for a island in grid2, use flag check if it is a sub-island

Approach 2: bfs
 */
public class Solution {
    public int countSubIslands1(int[][] grid1, int[][] grid2) {
        int res = 0;
        int m = grid2.length;
        int n = grid2[0].length;
        int[] flag = new int[1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid2[i][j] == 1){
                    dfs(grid2, grid1, i, j, flag);
                    if(flag[0] == 0) res++;
                    flag[0] = 0;
                }
            }
        }

        return res;
    }

    void dfs(int[][] grid2, int[][] grid1, int i, int j, int[] flag){
        if(i < 0 || i >= grid2.length || j < 0 || j >= grid2[0].length || grid2[i][j] != 1) return;

        if(grid1[i][j] == 0) flag[0] = 1;
        grid2[i][j] = 2;

        dfs(grid2, grid1, i - 1, j, flag);
        dfs(grid2, grid1, i + 1, j, flag);
        dfs(grid2, grid1, i, j - 1, flag);
        dfs(grid2, grid1, i, j + 1, flag);
    }


    public int countSubIslands2(int[][] grid1, int[][] grid2) {
        int res = 0;
        for(int i = 0; i < grid2.length; i++){
            for(int j = 0;  j < grid2[0].length; j++){
                if(grid2[i][j] == 1){
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    if(bfs(queue, grid1, grid2)){
                        res++;
                    }
                }
            }
        }

        return res;
    }

    boolean bfs(Queue<int[]> queue, int[][] grid1, int[][] grid2){
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean flag = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(grid1[cur[0]][cur[1]] == 0) flag = false;
            for(int[] direction: directions){
                int x = cur[0] + direction[0];
                int y = cur[1] + direction[1];
                if(x < 0 || x >= grid2.length || y < 0 || y >= grid2[0].length) continue;
                if(grid2[x][y] == 0) continue;
                queue.add(new int[]{x, y});
                grid2[x][y] = 0;
            }
        }

        return flag;
    }

    public static void main(String[] args) {

    }
}
