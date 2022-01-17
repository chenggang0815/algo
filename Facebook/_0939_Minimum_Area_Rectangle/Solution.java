package Facebook._0939_Minimum_Area_Rectangle;

import java.util.HashMap;
import java.util.HashSet;

/*
939. Minimum Area Rectangle
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.

Example 1:
Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
*/
/*
Solution
Approach 1: HashMap

4     *     *

2     *     *
1
      1 2   5

1: 1 2 4
2: 1 2 4
5: 1 2 4

1. if two point don't have same x or same y, it may can form a rectangle,
   for point (1,4) and (5,2)
2. we need to check if exists the other two point is (1,2) and (5,4) which can form a rectangle.
3. if it can form a rectangle we can calculate the area.
*/
public class Solution {
    public int minAreaRect(int[][] points) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int[] point : points){
            if(!map.containsKey(point[0])){
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }
        int res = Integer.MAX_VALUE;
        for(int[] point1 : points){
            for(int[] point2 : points){
                if(point1[0] == point2[0] || point1[1] == point2[1]) continue; // if two point don't have same x or same y, it may can form a rectangle,
                if(map.get(point1[0]).contains(point2[1]) && map.get(point2[0]).contains(point1[1])){ //we need to check if exists the other two point is (1,2) and (5,4) which can form a rectangle.
                    res = Math.min(res, Math.abs(point1[0] - point2[0]) * Math.abs(point1[1] - point2[1]));
                }
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
    public static void main(String[] args) {

    }
}
