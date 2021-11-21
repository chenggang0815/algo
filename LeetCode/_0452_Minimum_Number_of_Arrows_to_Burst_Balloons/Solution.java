package LeetCode._0452_Minimum_Number_of_Arrows_to_Burst_Balloons;
/*
452. Minimum Number of Arrows to Burst Balloons
There are some spherical balloons taped onto a flat wall that represents the XY-plane.
The balloons are represented as a 2D integer array points where points[i] = [x_start, x_end] denotes a balloon whose horizontal diameter stretches between x_start and x_end.
You do not know the exact y-coordinates of the balloons.
Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
A balloon with x_start and x_end is burst by an arrow shot at x if x_start <= x <= x_end.
There is no limit to the number of arrows that can be shot.
A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

Example 2:
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

Example 3:
Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

Constraints:
1. 1 <= points.length <= 10^5
2. points[i].length == 2
3. -2^31 <= x_start < x_end <= 2^31 - 1
*/

import java.util.Arrays;

/*
Solution
Approach 1 Greedy time:O(n*log(n)) space:O(1)
input = [[10,16],[2,8],[1,6],[7,12]] output = 2
sorted input = [[1,6],[2,8],[7,12],[10,16]]
res = res is the number of maximum interval
res = 1
[1,6]
  [2,8]
        [7,12]
           [10,16]

end = [1,6]
 end    intervals[i] (i=1,...,intervals.length-1)
[1,6] > [2,8] => if end  >= intervals[i][0] => continue
[1,6] < [7,12] => if end  < intervals[i][0] => res++; end=intervals[i][1]
[7,12] > [10,16] => if end  > intervals[i][0] => continue

similar to question 435
*/
public class Solution {
    int minArrows(int[][] points){
        //because -2^31 <= x_start < x_end <= 2^31 - 1 => a[1] - b[1] may overflow
        // so we use a[1] > b[1] ? -1 : 1 instead of a[1] - b[1]
        // we want a[1] < b[1] => a[1] - b[1] < 0 <=> a[1] > b[1] ? -1 : 1
        Arrays.sort(points, (a, b) -> a[1] > b[1] ? -1 : 1);
        int end = points[0][1];
        int res = 1;
        for (int i = 1; i < points.length; i++){
            if (end < points[i][0]){
                res++;
                end = points[i][1];
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
