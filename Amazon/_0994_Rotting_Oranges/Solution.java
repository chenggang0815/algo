package Amazon._0994_Rotting_Oranges;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] res = new int[]{0};

        int minutes = 0;
        int fresh = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 2){
                    isRotting(grid, i, j, res);
                }
                //if(grid[i][j] == 1 && isFresh(grid, i, j)) fresh++;
            }
        }

        System.out.println(res[0]);

        return res[0];

    }

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
   /*
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

     */return false;
    }

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
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) fresh++;
            }
        }
        if(fresh == 0) return 0;

        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }

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
                }
                if(i + 1 < grid.length && grid[i + 1][j] == 1){
                    queue.add(new int[]{i + 1, j});
                    grid[i + 1][j] = 2;
                    flag = true;
                }
                if(j - 1 >=0 && grid[i][j - 1] == 1){
                    queue.add(new int[]{i, j - 1});
                    grid[i][j - 1] = 2;
                    flag = true;
                }
                if(j + 1 < grid[0].length && grid[i][j + 1] == 1){
                    queue.add(new int[]{i, j + 1});
                    grid[i][j + 1] = 2;
                    flag = true;
                }
                size--;
            }
            if(flag == true) res++;
        }

        fresh = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) fresh++;
            }
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
