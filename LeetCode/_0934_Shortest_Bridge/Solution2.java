package LeetCode._0934_Shortest_Bridge;

import java.util.LinkedList;
import java.util.Queue;

/*

*/
public class Solution2 {
    /**
     * Use DFS + BFS to solve this WONDERFUL problem!
     * Step 1: use DFS to mark the first island to another number
     * Step 2: start from the first island, doing BFS level order traversal to find number of bridges (levels)
     * until reach the second island
     * */
    public int shortestBridge(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean marked = false;

        // DFS to mark the all positions of first island to 2
        for (int i = 0; i < n; i++) {
            // WARNING: MUST use a boolean variable to check if we already marked the first island to 2. Otherwise,
            // we will only break from the inner loop, but will not jump out the outer loop
            if (marked) {
                break;
            }
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // WARNING: MUST add all position of first island into queue as first level, they all can be the
                    // starting points of BFS level order traversal
                    dfs(grid, i, j, queue);
                    marked = true;
                    break;
                }
            }
        }

        // BFS level order traversal: to count number of levels before finding the second island
        int bridge = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curPoint = queue.poll();
                int x = curPoint[0];
                int y = curPoint[1];

                // once we find the second island, return current bridge value
                if (x > 0 && grid[x - 1][y] == 1 || x < n - 1 && grid[x + 1][y] == 1
                        || y > 0 && grid[x][y - 1] == 1 || y < m - 1 && grid[x][y + 1] == 1) {
                    return bridge;
                }

                // WARNING: CANNOT use if else statement, must use all if statement to check all four directions
                if (x > 0 && grid[x - 1][y] == 0) {
                    queue.offer(new int[]{x - 1, y});
                    grid[x - 1][y] = 2;
                }
                if (x < n - 1 && grid[x + 1][y] == 0) {
                    queue.offer(new int[]{x + 1, y});
                    grid[x + 1][y] = 2;
                }
                if (y > 0 &&grid[x][y - 1] == 0) {
                    queue.offer(new int[]{x, y - 1});
                    grid[x][y - 1] = 2;
                }
                if (y < m - 1 && grid[x][y + 1] == 0) {
                    queue.offer(new int[]{x, y + 1});
                    grid[x][y + 1] = 2;
                }
            }
            bridge++;
        }
        return bridge;
    }

    public void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 2;
        queue.offer(new int[]{i, j});
        dfs(grid, i - 1, j, queue);
        dfs(grid, i + 1, j, queue);
        dfs(grid, i, j - 1, queue);
        dfs(grid, i, j + 1, queue);
    }
    public static void main(String[] args) {

    }
}
