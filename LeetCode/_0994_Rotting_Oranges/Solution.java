package LeetCode._0994_Rotting_Oranges;

import java.util.LinkedList;
import java.util.Queue;
/*
994. Rotting Oranges

You are given an m x n grid where each cell can have one of three values:
0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

1. dfs
2 1 1
0 1 1
0 0 1

first recursion => j+1  m=3
second recursion => x+1 m=4
                    j+1 m=4
third recursion => x+1 m=5
                => j+1 m=5
fourth recursion => x+1 m=6

2.1 bfs
1. we iterate the matrix, every time we meet a rotten orange, we do a bfs
2. we use another matrix "steps" to record the current minimum step that the fresh orange need
3. if we start from another rotten orange, and the current step is lees than value in "steps", we update the value
4. finally, we need to iterate the "steps" to find the max steps

2.2 bfs
0. unlike the normal bfs question, instead of start from only one point, we may have multiple start points
1. since we may have multiple rotten oranges at the beginning
2. we put all the rotten orange in the queue, as our start points
3. then we do a bfs for each start points, mark the fresh orange to rotten oranges
4. until we marked all the fresh oranges that we can reach, the step is the minimum step we need.
for example:
2 2 1
1 1 1
0 1 1

we have two rotten oranges, we do a bfs for these two rotten oranges respectively.
step 1:
2 2 2
2 2 1
0 1 1
step 2:
2 2 2
2 2 2
0 2 1
step 3:
2 2 2
2 2 2
0 2 2
 */
public class Solution {
    /*
2 1 1
0 1 1
0 0 1
    * */
    public int orangesRotting(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2) dfs(grid, i, j, 2);
            }
        }
        int step = 2;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) return -1;
                step = Math.max(step, grid[i][j]);
            }
        }

        return step - 2;
    }

    void dfs(int[][] grid, int i, int j, int step){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;

        if(1 < grid[i][j] && grid[i][j] < step) return;

        grid[i][j] = step;
        dfs(grid, i - 1, j, step + 1);
        dfs(grid, i + 1, j, step + 1);
        dfs(grid, i, j - 1, step + 1);
        dfs(grid, i, j + 1, step + 1);
    }

/*
    static boolean isRotting(int[][] grid, int i, int j, int[] res){
        int rows = grid.length;
        int cols = grid[0].length;
        //System.out.println(i);
        if(i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == -1) return false;

        if(i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == 1){
            grid[i][j] = -1;
            return true;
        }
        grid[i][j] = -1;
        if(isRotting(grid, i - 1, j, res) ||
        isRotting(grid, i + 1, j, res) ||
        isRotting(grid, i, j - 1, res) ||
        isRotting(grid, i, j + 1, res)) res[0]++;

        if(i - 1 >= 0 && grid[i - 1][j] == 1){
            grid[i - 1][j] = 2;
            res[0]++;
            isRotting(grid, i - 1, j, res);
        }
        if(i + 1 < rows && grid[i + 1][j] == 1){
            grid[i + 1][j] = 2;
            res[0]++;
            isRotting(grid, i + 1, j, res);
        }

        if(j - 1 >= 0 && grid[i][j - 1] == 1){
            grid[i][j - 1] = 2;
            res[0]++;
            isRotting(grid, i, j - 1, res);
        }

        if(j + 1 < cols && grid[i][j + 1] == 1){
            grid[i][j + 1] = 2;
            res[0]++;
            isRotting(grid, i, j + 1, res);
        }

     return false;
    }
*/
    public boolean isFresh(int[][] grid, int i, int j){
        int rows = grid.length;
        int cols = grid[0].length;

        boolean res = true;
        if(i - 1 >= 0 && grid[i - 1][j] == 2){
            res = false;
        }
        if(i + 1 < rows && grid[i + 1][j] == 2){
            res = false;
        }

        if(j - 1 >= 0 && grid[i][j - 1] == 2){
            res = false;
        }

        if(j + 1 < cols && grid[i][j + 1] == 2){
            res = false;
        }

        return res;
    }


    // bfs 2.2
    int orangesRotting2(int[][] grid) {
        int freshCnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) freshCnt++;
                if(grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }
        if(freshCnt == 0) return 0;

        int res = 0;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            boolean isMove = false;
            while(levelSize > 0){
                int[] cur = queue.poll();
                for(int[] dir: directions){
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    freshCnt--;
                    isMove = true;
                    queue.add(new int[]{x, y});
                }
                levelSize--;
            }
            if(isMove) res++;
        }

        if(freshCnt > 0) return -1;

        return res;
    }

    public static void main(String[] args) {
        //int[][] nums = new int[][]{{1,2,1},{0,0,2},{2,1,1}};
        int[][] nums = new int[][]{{1,1,2},{1,0,2},{0,0,0}};
        //System.out.println(orangesRotting2(nums));
    }
}
