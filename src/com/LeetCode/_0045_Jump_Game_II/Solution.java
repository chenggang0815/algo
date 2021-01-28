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
思路1：反向查找出发位置
https://leetcode-cn.com/problems/jump-game-ii/solution/tiao-yue-you-xi-ii-by-leetcode-solution/

思路2：正向查找可到达的最大位置

1. 维护当前能够到达的最大下标位置，记为边界(max_distance)
2. 从左到右遍历数组，到达边界时（i==index），更新边界并将跳跃次数增加1
3. 在遍历数组时，不访问最后一个元素(i < nums.length - 1)，这是因为在访问最后一个元素之前,边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了
4. 如果访问最后一个元素，在边界正好为最后一个位置的情况下，会增加一次「不必要的跳跃次数」，因此不必访问最后一个元素

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
        while (i < length - 1){
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
