package Facebook._0778_Swim_in_Rising_Water;

import java.util.PriorityQueue;

/*
778. Swim in Rising Water
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
*/
/*
Solution
Approach 1: priorityQueue
*/
public class Solution {
    public int swimInWater(int[][] grid) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{0,0, grid[0][0]});
        int res = 0;
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int x = point[0], y = point[1], time = point[2];
            for(int[] direction : directions){
                int x1 = x + direction[0];
                int y1 = y + direction[1];
                if(x1 < 0 || x1 >= grid.length || y1 < 0 || y1 >= grid[0].length) continue;
                if(visited[x1][y1]) continue;
                visited[x1][y1] = true;
                res = Math.max(time, grid[x1][y1]);
                if(x1 == grid.length - 1 && y1 == grid[0].length - 1) return res;

                queue.add(new int[]{x1, y1, res});
            }
        }

        return 0;
    }
    public static void main(String[] args) {

    }
}
