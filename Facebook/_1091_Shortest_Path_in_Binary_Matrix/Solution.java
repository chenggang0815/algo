package Facebook._1091_Shortest_Path_in_Binary_Matrix;

import java.util.LinkedList;
import java.util.Queue;

/*
1091. Shortest Path in Binary Matrix
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
    1. All the visited cells of the path are 0.
    2. All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 2:
Input: grid = [[0,0,0],
               [1,1,0],
               [1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],
               [1,1,0],
               [1,1,0]]
Output: -1
*/
/*
Solution
Approach 1: BFS
1. for 8 direction, we operate bfs until we reach the grid[grid.length - 1][grid[0].length - 1]

*/
public class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,-1}, {-1,1}};
        Queue<int[]> queue = new LinkedList<>();
        // corner case
        if(grid[0][0] == 0) queue.add(new int[]{0,0,1});
        else return -1;
        int[][] visited = new int[grid.length][grid[0].length];
        while(!queue.isEmpty()){
            int[] index = queue.poll();
            int x = index[0];
            int y = index[1];
            int distance = index[2];
            if(x == grid.length - 1 && y == grid[0].length - 1) return distance;
            for(int[] direction : directions){
                int x1 = x + direction[0];
                int y1 = y + direction[1];
                if(x1 < 0 || x1 > grid.length - 1 || y1 < 0 || y1 > grid[0].length - 1 || grid[x1][y1] == 1){
                    continue;
                }
                if(visited[x1][y1] == 1) continue;
                visited[x1][y1] = 1;
                //System.out.print(Arrays.toString(new int[]{x1, y1, distance + 1}));
                queue.add(new int[]{x1, y1, distance + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) {

    }
}
