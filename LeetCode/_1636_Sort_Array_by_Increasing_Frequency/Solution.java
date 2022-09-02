package LeetCode._1636_Sort_Array_by_Increasing_Frequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
1636. Sort Array by Increasing Frequency

Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
*/
/*
Approach: use two hashMap,
1. <num, cnt>
2. <cnt, list<num>>
3. the range of cnt is 1 to nums.length, so we can get the list<num> from second map and put num into result array
*/
public class Solution {
    public int[] frequencySort(int[] nums) {
        //<cnt, list<num>>
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // <num, cnt>
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int num: nums){
            int cnt = cntMap.getOrDefault(num, 0);
            cntMap.put(num, cnt + 1);
        }

        for(int num: nums){
            int cnt = cntMap.get(num);
            if(!map.containsKey(cnt)){
                map.put(cnt, new ArrayList<>());
            }
            map.get(cnt).add(num);
        }

        int[] res = new int[nums.length];
        int index = 0;
        for(int i = 0; i <= nums.length; i++){
            if(map.containsKey(i)){
                List<Integer> list = map.get(i);
                list.sort((a,b) -> b - a);
                for(int num: list) res[index++] = num;
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
