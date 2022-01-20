package Facebook._0329_Longest_Increasing_Path_in_a_Matrix;

import java.util.LinkedList;
import java.util.Queue;

/*
329. Longest Increasing Path in a Matrix
Given an m x n integers matrix, return the length of the longest increasing path in matrix.
From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

Example 1:
Input: matrix = [[9,9,4],
                 [6,6,8],
                 [2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
    [[7,8,9],
     [9,7,6],
     [7,2,3]]
output: 6 =>  The longest increasing path is [2, 3, 6, 7, 8, 9].
*/
/*
Solution
Approach 1: BFS => Time Limit Exceeded

Approach 2: DFS + Memoization
*/
public class Solution {
    // approach 1
    public int longestIncreasingPath1(int[][] matrix) {
        int res = 1;
        int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        //int[][] cache = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                queue.add(new int[]{i, j, 1});
                //int[][] visited = new int[rows][cols];
                // we don't need visited set, becuase the path is increased
                while(!queue.isEmpty()){
                    int[] cur = queue.poll();
                    int x = cur[0];
                    int y = cur[1];
                    int d = cur[2];
                    for(int[] direction : directions){
                        int x1 = x + direction[0];
                        int y1 = y + direction[1];
                        if(x1 < 0 || x1 >= rows || y1 < 0 || y1 >= cols || matrix[x1][y1] <= matrix[x][y]) continue;
                        //if(visited[x1][y1] == 1) continue;
                        //visited[x1][y1] = 1;
                        //System.out.print(Arrays.toString(new int[]{x1, y1, matrix[x1][y1]}) + "\n");
                        res = Math.max(res, d + 1);
                        queue.add(new int[]{x1, y1, d + 1});
                    }
                }
            }
        }

        return res;
    }

    // approach 2


    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache, directions));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache, int[][] directions) {
        if (cache[i][j] != 0) return cache[i][j];
        for (int[] d : directions) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < matrix.length && 0 <= y && y < matrix[0].length && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache, directions));
        }
        cache[i][j]++;

        return cache[i][j];
    }

    public static void main(String[] args) {

    }
}
