package Facebook._0296_Best_Meeting_Point;

import java.util.ArrayList;
import java.util.List;

/*
296. Best Meeting Point
Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example 1:
Input: grid = [[1,0,0,0,1],
               [0,0,0,0,0],
               [0,0,1,0,0]]
Output: 6
Explanation: Given three friends living at (0,0), (0,4), and (2,2).
The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
So return 6.

Constraints:
1. m == grid.length
2. n == grid[i].length
3. 1 <= m, n <= 200
4. grid[i][j] is either 0 or 1.
5. There will be at least two friends in the grid.
*/
/*
Solution
Approach 1: brute force
Approach 2
1. first we consider one dimension, [1,0,1,0,1] => the index of 1 is [0,2,4], the mid point is the median point
2. the median point is 2, of the distance is |0-2| + |2-2| + |4-2| = 6
3. for the 2d matrix, the column and the row is independent, for example:
Input: grid = [[1,0,0,0,1],
               [0,0,0,0,0],
               [0,0,1,0,0]]
for row, we have [1,0,1,0,1] => (0,2,4)
for col, we have  => (0,2,0) => sort(0,0,2)
*/
public class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                if(grid[row][col] == 1) rows.add(row);
            }
        }
        for(int col = 0; col < grid[0].length; col++){
            for(int row = 0; row < grid.length; row++){
                if(grid[row][col] == 1) cols.add(col);
            }
        }

        //System.out.print(rows);
        //System.out.print(cols);

        return getDistance(rows) + getDistance(cols);
    }

    int getDistance(List<Integer> list){
        int distance = 0;
        int mid = list.get(list.size() / 2);
        for(int num : list){
            distance += Math.abs(num - mid);
        }

        return distance;
    }
    public static void main(String[] args) {

    }
}
