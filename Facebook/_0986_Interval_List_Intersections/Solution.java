package Facebook._0986_Interval_List_Intersections;

import java.util.ArrayList;
import java.util.List;

/*
986. Interval List Intersections
You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [start_i, end_i] and secondList[j] = [start_j, end_j].
Each list of intervals is pairwise disjoint and in sorted order.
Return the intersection of these two interval lists.
A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].

Example 1:
Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
*/
/*
Solution

*/
public class Solution {
    //[0,2] [1,5] => (1,2)
    //  i++
    //[1,5] [5,10]
    //        j++
    //[5,10] [8,12]
    //
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;

        List<int[]> res = new ArrayList<>();
        // int[] temp = new int[2];
        // if(firstList[0][1] < secondList[0][1]){
        //     temp = secondList[0];
        // }else{
        //     temp = firstList[0];
        // }

        while(i < firstList.length && j < secondList.length){
            int low = Math.max(firstList[i][0], secondList[j][0]);
            int high = Math.min(firstList[i][1], secondList[j][1]);
            if(low <= high) res.add(new int[]{low, high}); //firstList[i]=[8, 12] secondList[j]=[13, 23]

            if(firstList[i][1] < secondList[j][1]){
                i++;
            }else{
                j++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
    public static void main(String[] args) {

    }
}
