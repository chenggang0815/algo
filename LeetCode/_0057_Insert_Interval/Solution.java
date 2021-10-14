package LeetCode._0057_Insert_Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
57. Insert Interval

You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
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
1. compare new[start, end] and interval[start, end]
   if interval[end] < new[start] => res.add(interval)
   for example: intervals=[[1,2], [3,4], [7,8]] new=[100,101] => [[1,2], [3,4], [7,8], [100,101]]
                                   => |_______________|          |_______________|
                                                interval[end] new[start]
2. until interval[start] < new[end] => interval[end] >= new[start] && interval[start] < new[end]
                        only interval[start] < new[end]
                                    => |______________|           |____________|
                           interval[start]     interval[end]    new[start]    new[end]


                                    => |______________|_____________|____________|
                           interval[start]     new[start]    interval[end]    new[end]
                    Math.min(newInterval[0], intervals[i][0])
                    Math.max(newInterval[1], intervals[i][1])}
   for example: intervals=[1,2, [3,4], [100, 105]] new=[100, 101]
*/
public class Solution {
    static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        //
        int flag = 0;
        for(int[] interval: intervals){
            if(flag == 1){
                res.add(interval);
                continue;
            }
            if(interval[1] < newInterval[0]){
                res.add(interval);
                continue;
            }
            System.out.print(Arrays.toString(newInterval));
            System.out.println("=====");
            if(interval[0] > newInterval[1]){
                res.add(newInterval);
                res.add(interval);
                flag = 1;
                continue;
            }
            if(interval[0] > newInterval[0] && interval[1] < newInterval[1]) continue;
            if(interval[1] >= newInterval[0]){
                newInterval[0] = interval[0];
                continue;
            }
            if(interval[0] <= newInterval[1]){
                newInterval[1] = interval[1];
            }
        }

        int[][] nums = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++){
            nums[i] = res.get(i);
        }

        return nums;
    }

    static int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;

        while (i < intervals.length && intervals[i][1] < newInterval[0]){
            result.add(intervals[i++]);
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval = new int[]{
                    Math.min(newInterval[0], intervals[i][0]),
                    Math.max(newInterval[1], intervals[i][1])};
            i++;
        }

        System.out.println(Arrays.toString(newInterval));

        result.add(newInterval); // add the union of intervals we got

        while (i < intervals.length) result.add(intervals[i++]);

        int[][] nums = new int[result.size()][2];
        for(int j = 0; j < result.size(); j++){
            nums[j] = result.get(j);
        }

        return nums;
    }

    public static void main(String[] args) {
        int[][] res = insert2(new int[][]{{1,3}, {6,9}}, new int[]{2,5});
    }
}
