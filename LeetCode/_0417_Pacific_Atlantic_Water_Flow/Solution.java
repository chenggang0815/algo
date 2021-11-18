package LeetCode._0417_Pacific_Atlantic_Water_Flow;
/*
417. Pacific Atlantic Water Flow
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
*/

import java.util.*;

/*
solution
Approach 1: bfs

Approach 2: dfs

*/
public class Solution {
    public List<List<Integer>> pacificAtlantic1(int[][] heights) {
        Queue<int[]> p = new LinkedList<>();
        Queue<int[]> a = new LinkedList<>();
        int rows = heights.length;
        int cols = heights[0].length;
        for(int i = 0; i < rows; i++){
            p.add(new int[]{i, 0});
            a.add(new int[]{i, cols - 1});
        }
        for(int i = 0; i < cols; i++){
            p.add(new int[]{0, i});
            a.add(new int[]{rows - 1, i});
        }
        boolean[][] reachable1 = bfs(p, heights, rows, cols);
        boolean[][] reachable2 = bfs(a, heights, rows, cols);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(reachable1[i][j] && reachable2[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    boolean[][] bfs(Queue<int[]> queue, int[][] heights, int rows, int cols){
        int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        boolean[][] visited = new boolean[rows][cols];
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            visited[point[0]][point[1]] = true;
            for(int[] direction : directions){
                int x = point[0] + direction[0];
                int y = point[1] + direction[1];
                if(x < 0 || x >= rows || y < 0 || y >= cols) continue;
                if(visited[x][y]) continue;
                if(heights[point[0]][point[1]] > heights[x][y]) continue;

                queue.add(new int[]{x, y});
                visited[x][y] = true; // lc 200 如果不加这一行过不了
            }
        }

        return visited;
    }

    // dfs
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            dfs(i, 0, pacific, heights);
            dfs(i, cols - 1, atlantic, heights);
        }
        for(int j = 0; j < cols; j++){
            dfs(0, j, pacific, heights);
            dfs(rows - 1, j, atlantic, heights);
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    void dfs(int i, int j, boolean[][] matrix, int[][] heights){
        matrix[i][j] = true;
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] direction : directions){
            int x = i + direction[0];
            int y = j + direction[1];
            if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) continue;
            if(matrix[x][y]) continue;
            if(heights[i][j] > heights[x][y]) continue;
            dfs(x, y, matrix, heights);
        }
    }
    public static void main(String[] args) {

    }
}
