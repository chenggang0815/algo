package Amazon._0994_Rotting_Oranges;

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
 */
public class Solution {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 2){
                    helper(grid, i, j, 2);
                }
            }
        }

        int minutes = 2;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1) return -1;
                minutes = Math.max(minutes, grid[i][j]);
            }
        }

        return minutes - 2;

    }


    void helper(int[][] grid, int i, int j, int minutes){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
                grid[i][j] == 0 ||
                (1 < grid[i][j] && grid[i][j] < minutes)) return;

        grid[i][j] = minutes;
        helper(grid, i - 1, j, minutes + 1);
        helper(grid, i + 1, j, minutes + 1);
        helper(grid, i, j - 1, minutes + 1);
        helper(grid, i, j + 1, minutes + 1);
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


    static int orangesRotting2(int[][] grid) {
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) fresh++;
                if(grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }
        if(fresh == 0) return 0;

        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            boolean flag = false;
            while(size > 0){
                int[] index = queue.poll();
                int i = index[0];
                int j = index[1];
                if(i - 1 >=0 && grid[i - 1][j] == 1){
                    queue.add(new int[]{i - 1, j});
                    grid[i - 1][j] = 2;
                    flag = true;
                    fresh--;
                }
                if(i + 1 < grid.length && grid[i + 1][j] == 1){
                    queue.add(new int[]{i + 1, j});
                    grid[i + 1][j] = 2;
                    flag = true;
                    fresh--;
                }
                if(j - 1 >=0 && grid[i][j - 1] == 1){
                    queue.add(new int[]{i, j - 1});
                    grid[i][j - 1] = 2;
                    flag = true;
                    fresh--;
                }
                if(j + 1 < grid[0].length && grid[i][j + 1] == 1){
                    queue.add(new int[]{i, j + 1});
                    grid[i][j + 1] = 2;
                    flag = true;
                    fresh--;
                }
                size--;
            }
            if(flag == true) res++;
        }

        if(fresh > 0) return -1;

        return res;
    }

    public static void main(String[] args) {
        //int[][] nums = new int[][]{{1,2,1},{0,0,2},{2,1,1}};
        int[][] nums = new int[][]{{1,1,2},{1,0,2},{0,0,0}};
        System.out.println(orangesRotting2(nums));
    }
}
