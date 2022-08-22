package LeetCode._0435_Non_overlapping_Intervals;
import java.util.Arrays;
/*
435. Non-overlapping Intervals
Given an array of intervals intervals where intervals[i] = [start_i, end_i],
return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:
1. 1 <= intervals.length <= 105
2. intervals[i].length == 2
3. -5 * 104 <= start_i < end_i <= 5 * 104
*/
/*
Solution
Approach1 Greedy time:O(nlog(n)) space:O(1)
1. sort by the end time
intervals = [[1,2],[2,3],[3,4],[1,3]]
sorted intervals = [[1,2],[2,3],[1,3],[3,4]]
2. res = 0 => res is the number of minimum intervals need to be removed
3. end = intervals[0][1]
     end   intervals[i] (i=1,...,intervals.length-1)
=>  [1,2]   [2,3] => if end <= intervals[i][0] => end = intervals[i][1]
=>  [2,3]   [1,3] => else => end > intervals[i][0] => res++;
=>  [2,3]   [3,4] => end <= intervals[i][0] => end = intervals[i][1]
return res = 1

Approach2 Greedy time:O(nlog(n)) space:O(1)
1. sort by the end time
intervals = [[1,2],[2,3],[3,4],[1,3]]
sorted intervals = [[1,2],[2,3],[1,3],[3,4]]
2. res = 1 => res is the number of maximum interval after remove overlap intervals
2. end = intervals[0][1]
     end   intervals[i] (i=1,...,intervals.length-1)
=>  [1,2]   [2,3] => if end <= intervals[i][0] => res++; end = intervals[i][1]
=>  [2,3]   [1,3] => else => end > intervals[i][0] => continue
=>  [2,3]   [3,4] => end <= intervals[i][0] => res++; end = intervals[i][1]
res = 2
return intervals.length - res = 3 - 2 = 1


Approach3  time:O(nlog(n)) space:O(1)
1. sort by the start time


similar to question 452
*/
public class Solution {
    // approach1
    // [[1,100],[11,22],[1,11],[2,12]]
    int eraseOverlapIntervals1(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));
        int res = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++){
            if (end > intervals[i][0]){
                res++;
            }else {
                end = intervals[i][1];
            }
        }

        return res;
    }

    // approach 2
    int eraseOverlapIntervals2(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));
        int res = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++){
            if (end <= intervals[i][0]){
                res++;
                end = intervals[i][1];
            }
        }

        return intervals.length - res;
    }

    // approach 3
    public int eraseOverlapIntervals3(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int res = 0;
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(end <= intervals[i][0]){
                end = intervals[i][1];
            }else{
                end = Math.min(end, intervals[i][1]);
                res++;
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
