package LeetCode._0056_Merge_Intervals;
/*
56. Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
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
    4.1 if there is nothing in list, just add the interval to the list
    4.2 if list.get(list.size() - 1)[1] < interval[0], add the interval to the list
    4.3 else => merge two interval
             => list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], interval[1])
 */

/*
1. 因为给定的输入不一定是example 1中这样每个区间的左端点升序排序
2. 所以需要按照每个区间的左端给数组排序，剩下就是依次比较两个挨着的区间是否有重叠 => 当前区间的右端点 > 下一个区间的左端点 => 有重叠
    2.1 若无重叠，直接把当前区间放进去
    2.2 若有重叠，需要合并区间，因为已经是按照左端点排序的，所以只需要比较右边端点，并且取最大值

ps：人生苦短 我用python
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*

*/
public class Solution {
    // 第一版40ms https://leetcode.com/submissions/detail/450064142/

    //10 ms
    static int[][] merge1(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });


        List<int[]> list = new ArrayList<>();
        int i = 0;
        while (i < length){
            if(i < length - 1 && intervals[i][1] >= intervals[i + 1][0]){
                int right = Math.max(intervals[i][1], intervals[i+1][1]);
                int[] temp = new int[]{intervals[i][0], right};
                intervals[i + 1] = temp;
                if (list.size() > 0 && list.get(list.size() - 1)[1] >= temp[0]){
                    list.remove(list.size() - 1);
                }
                list.add(temp);
            }else {
                if (list.size() > 0 && list.get(list.size() - 1)[1] < intervals[i][0]) list.add(intervals[i]);
                if (list.size() == 0) list.add(intervals[i]);
            }
            i++;
        }

        int[][] res = new int[list.size()][2];
        for(int j = 0; j < list.size(); j++){
            System.out.println(Arrays.toString(list.get(j)));
            res[j] = list.get(j);

        }

        return res;
    }

    // 6ms
    static int[][] merge2(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < length; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            if(res.size() == 0 || res.get(res.size() - 1)[1] < left){
                res.add(intervals[i]);
            }else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], right);
            }
        }

        return  res.toArray(new int[res.size()][]);
    }


    public static void main(String[] args) {
        //int[][] res = merge(new int[][]{{1,4},{0,2},{3,5}});
        //merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        merge2(new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}});

    }
}
