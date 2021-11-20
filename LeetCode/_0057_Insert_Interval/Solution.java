package LeetCode._0057_Insert_Interval;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/*
57. Insert Interval

You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.

You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
Return intervals after the insertion.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Example 3:
Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]

Example 4:
Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]

Example 5:
Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]
*/
/*
Solution:
Approach 1: time:O(n) space:O(n)
1. because the intervals is sorted in ascending order by intervals[i][0]
2. iterate the intervals, for each interval and newInterval, we have 3 case:
case 1 => if interval[1] < newInterval[1]
       => res.add(interval)
                                        |_________|
                               interval[0]     interval[1]
                                                                     |________|
                                                          newInterval[0]   newInterval[1]

case 2 => else if newInterval[1] < interval[0]
       => res.add(newInterval)
       => newInterval = interval //update newInterval
                                                                    |_________|
                                                          interval[0]     interval[1]
                                     |__________|
                          newInterval[0]   newInterval[1]

case 3 => else, merge interval and newInterval
        => newInterval[0] = Math.min(newInterval[0], intervals[i][0])
        => newInterval[1] = Math.max(newInterval[1], intervals[i][1])}
                                        |__________________|
                                     interval[0]         interval[1]
                                                     |__________________|
                                              newInterval[0]        newInterval[1]

                                                      |__________________|
                                                   interval[0]         interval[1]
                                       |__________________|
                              newInterval[0]        newInterval[1]

Approach 2:
1. compare interval[1] and newInterval[0]
   while(interval[1] < newInterval[0]) => res.add(interval)

2. until interval[0] < newInterval[1]
  2.1 because the condition interval[end] >= new[start] already exists
  2.2 then we have condition, interval[end] >= new[start] && interval[start] < new[end]
  2.3 these two intervals must have overlap
*/
public class Solution {
    // Approach1
    static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] interval: intervals){
            if(interval[1] < newInterval[0]) res.add(interval);
            else if (newInterval[1] < interval[0]){
                res.add(newInterval);
                newInterval = interval;
            }else{
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        res.add(newInterval);
        int[][] nums = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) nums[i] = res.get(i);

        return nums;
    }

    // Approach2
    static int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) result.add(intervals[i++]);
        while (i < intervals.length && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        while (i < intervals.length) result.add(intervals[i++]);
        int[][] nums = new int[result.size()][2];
        for(int j = 0; j < result.size(); j++) nums[j] = result.get(j);

        return nums;
    }

    public static void main(String[] args) {
        int[][] res = insert2(new int[][]{{1,3}, {6,9}}, new int[]{2,5});
    }
}
