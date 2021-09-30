package Amazon._0056_Merge_Intervals;

import java.util.Arrays;
import java.util.LinkedList;

/*
56. Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/
/*
solution 1: brute force


solution 2:
1. sort by left value of every interval
2. compare intervals[i][1] intervals[i + 1][0]
   2.1 if intervals[i][1] >= intervals[i + 1][0] => merge
       ex1: [1,3] [2,4] => [1,4]
       ex2: [1,4] [2,3] => [1,4]
   new interval = [left, right]
   left = intervals[i][0]
   right = Math.max(intervals[i][1], intervals[i + 1][1])

3. for the current interval, we need to compare with the previous interval to check if the two interval can merge

4. add all the interval in a dynamic array,
    4.1 if there is nothing in my list, just add the interval to the list
    4.2 if list.get(list.size() - 1)[1] < interval[0], add the interval to the list
    4.3 else => merge two interval
             => list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], interval[1])
 */
public class Solution {

    static int[][] merge(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        LinkedList<int[]> res = new LinkedList<>();
        for (int[] interval: intervals){
            if (res.isEmpty() || res.getLast()[1] < interval[0]){
                res.add(interval);
            }else{
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }

        return res.toArray(new int[res.size()][]);

    }

    public static void main(String[] args) {

    }
}
