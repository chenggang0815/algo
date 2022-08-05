package LeetCode._0934_Shortest_Bridge;

import java.util.LinkedList;
import java.util.Queue;

/*

*/
public class Solution {
    static int shortestBridge(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int minStep = Integer.MAX_VALUE;
        int[][] visited = new int[grid.length][grid[0].length];
        boolean flipped = false;
        for(int m = 0 ; m < grid.length; m++){
            if (flipped) break;
            for(int n = 0; n < grid[0].length; n++) {
                if (grid[m][n] == 1) queue.add(new int[]{m, n, 1, 0});
        /*
[[0,1,0,0,0],
 [0,1,0,1,1],
 [0,0,0,0,1],
 [0,0,0,0,0],
 [0,0,0,0,0]]
        */

                while (!queue.isEmpty()) {
                    int[] point = queue.poll();
                    int i = point[0];
                    int j = point[1];
                    int inIsland = point[2];
                    int step = point[3];
                    if (visited[i][j] == 1) continue;
                    visited[i][j] = 1;

                    if (grid[i][j] == 1 && inIsland == 0) {
                        minStep = Math.min(minStep, step - 1);
                    }

                    if (i < grid.length - 1 && visited[i + 1][j] != 1) {
                        if (grid[i + 1][j] == 0) inIsland = 0;
                        queue.add(new int[]{i + 1, j, inIsland, step + 1});
                    }
                    if (i > 0 && visited[i - 1][j] != 1) {
                        if (grid[i - 1][j] == 0) inIsland = 0;
                        queue.add(new int[]{i - 1, j, inIsland, step + 1});
                    }

                    if (j < grid[0].length - 1 && visited[i][j + 1] != 1) {
                        if (grid[i][j + 1] == 0) inIsland = 0;
                        queue.add(new int[]{i, j + 1, inIsland, step + 1});
                    }

                    if (j > 0 && visited[i][j - 1] != 1) {
                        if (grid[i][j - 1] == 0) inIsland = 0;
                        queue.add(new int[]{i, j - 1, inIsland, step + 1});
                    }
                }
            }
        }
        return minStep;
    }

    public static void main(String[] args) {
        /*
                /*
[[0,1,0,0,0],
 [0,1,0,1,1],
 [0,0,0,0,1],
 [0,0,0,0,0],
 [0,0,0,0,0]]
        */

        int[][] grid = new int[][]{{0,1,0,0,0},{0,1,0,1,1},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(shortestBridge(grid));
    }
}
