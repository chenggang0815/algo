package LeetCode._0252_Meeting_Rooms;
import java.util.Arrays;
/*
252. Meeting Rooms
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: false

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: true

Constraints:
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106
*/
/*
Solution: time:O(nlog(n))
1. sort by the start time, if intervals[i][1] > intervals[i+1][0] => return false
 => [[2,4],[7,10]]
*/
public class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for(int i = 0; i < intervals.length - 1; i++){
            if(intervals[i][1] > intervals[i + 1][0]) return false;
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
