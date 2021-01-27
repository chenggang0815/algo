package com.LeetCode._0045_Jump_Game_II;
/*
45. Jump Game II

Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.
You can assume that you can always reach the last index.



Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
[3,4,3,1,2,2]
 */

/*
time: o(n)
space: o(1)
 */
public class Solution {
    static int jump(int[] nums) {
        int length = nums.length;
        int i = 0;
        int index = 0;
        int max_distance = 0;
        int res = 0;
        while (i < length){
            max_distance = Math.max(max_distance, i + nums[i]);
            if (i == index){
                res++;
                index = max_distance;
            }
            i++;
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
}
