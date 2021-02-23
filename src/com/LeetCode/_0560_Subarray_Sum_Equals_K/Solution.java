package com.LeetCode._0560_Subarray_Sum_Equals_K;
/*
560. Subarray Sum Equals K

Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2
 */
public class Solution {

    static int subarraySum(int[] nums, int k) {
        int res = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            if (dp[i - 1] == k){
                res++;
                dp[i - 1] = 0;
            }
            if (dp[i - 1] > k) dp[i - 1] = 0;
            dp[i] = dp[i - 1] + nums[i];
            if (dp[i] == k){
                res++;
                dp[i] = dp[i - 1];
            }
            if (dp[i] > k) dp[i] = 0;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1,2,3}, 2));
    }
}
