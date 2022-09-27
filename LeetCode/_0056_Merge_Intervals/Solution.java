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

Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= start_i <= end_i <= 104
*/

/*
Solution:
Approach 1: brute force

Approach 2: time:O(nlog(n)) space:O(n)
similar to question 57
1. sort by intervals[i][0]
2. because 1 <= intervals.length => res.add(intervals[0])
3. iterate the array, from i=1, there exists two case,
   case 1: => if res.get(res.size()-1)[1] < intervals[i][0]

                                           |_______________|
                     res.get(res.size()-1)[0]    res.get(res.size()-1)[1]
                                                                     |_______________|
                                                               intervals[i][0]   intervals[i][1]

    case 2: else (because we already sorted by the intervals[i][0]):
            => res.get(res.size() - 1)[0] = Math.min(res.get(res.size() - 1)[0], intervals[i][0]);
            => res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], intervals[i][1]);

                                               |_______________|
                          res.get(res.size()-1)[0]    res.get(res.size()-1)[1]
                                                          |_______________|
                                                  intervals[i][0]   intervals[i][1]
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
    //10 ms
    static int[][] merge1(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) return intervals;

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);


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
        for(int j = 0; j < list.size(); j++) res[j] = list.get(j);

        return res;
    }

    static int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            if(res.get(res.size() - 1)[1] < intervals[i][0]){
                res.add(intervals[i]);
            }else{
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], intervals[i][1]);
            }
        }
        int[][] array = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) array[i] = res.get(i);

        return array;
    }


    public static void main(String[] args) {
        //int[][] res = merge(new int[][]{{1,4},{0,2},{3,5}});
        //merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        merge2(new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}});

    }
}
