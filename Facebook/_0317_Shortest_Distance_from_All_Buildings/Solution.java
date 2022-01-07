package Facebook._0317_Shortest_Distance_from_All_Buildings;

import java.util.LinkedList;
import java.util.Queue;

/*
317. Shortest Distance from All Buildings

You are given an m x n grid grid of values 0, 1, or 2, where:
1. each 0 marks an empty land that you can pass by freely,
2. each 1 marks a building that you cannot pass through, and
3. each 2 marks an obstacle that you cannot pass through.
You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
Example 1:
Input: grid = [[1,0,2,0,1],
               [0,0,0,0,0],
               [0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
*/
/*
Solution
Approach 1: BFS
1. iterate the grid matrix, for each 0, start a BFS:
2. for each bfs, traverse all 4-directionally adjacent cells that are not blocked or visited and keep track of the distance from the start cell.
   2.1 each iteration adds 1 to the distance.
   2.2 every time we reach a house => if(grid[x][y] == 1) => one-- and curDistance += distance
   2.3 If one == 0, we meet all the house, then update the total distance.
3. Otherwise, the starting cell (and every cell visited during this BFS) cannot reach all of the houses. So set every visited empty land cell equal to 2 so that we do not start a new BFS from that cell and return INT_MAX.
4. Each time a total distance is returned from a BFS call, update the minimum distance (minDistance).
5. If it is possible to reach all houses from any empty land cell, then return the minimum distance found. Otherwise, return -1.
*/
public class Solution {
    public int shortestDistance(int[][] grid) {
        int oneCnt = 0;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) oneCnt++;
            }
        }
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j, 0});
                    int[][] visited = new int[grid.length][grid[0].length];
                    int one = oneCnt;
                    int currentDistance = 0;
                    while(!queue.isEmpty()){
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        int d = current[2];
                        if(visited[x][y] == 1 || grid[x][y] == 2) continue;
                        visited[x][y] = 1;
                        if(grid[x][y] == 1){
                            one--;
                            currentDistance += d;
                            if(one == 0){
                                // System.out.print(i + "==" + j + "\n");
                                //  System.out.print(currentDistance + "\n");
                                res = Math.min(res, currentDistance);
                            }
                            continue;
                        }

                        if(x > 0 ) queue.add(new int[]{x - 1, y, d + 1});
                        if(x < grid.length - 1 ) queue.add(new int[]{x + 1, y, d + 1});
                        if(y > 0 ) queue.add(new int[]{x, y - 1, d + 1});
                        if(y < grid[0].length - 1 ) queue.add(new int[]{x, y + 1, d + 1});
                    }
                    // If we did not reach all houses, then any cell visited also cannot reach all houses.
                    // Set all cells visited to 2 so we do not check them again.
                    if(one != 0){
                        for(int row = 0; row < grid.length; row++){
                            for(int col = 0; col < grid[0].length; col++){
                                if(grid[row][col] == 0 && visited[row][col] == 1){
                                    grid[row][col] = 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
    public static void main(String[] args) {

    }
}
