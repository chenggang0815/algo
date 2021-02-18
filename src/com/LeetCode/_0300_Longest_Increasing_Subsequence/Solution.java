package com.LeetCode._0300_Longest_Increasing_Subsequence;

import java.util.Arrays;

/*
300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */

/*
思路1：动态规划 - time:o(n^2) space:o(n)

1. dp[i]的值代表以nums[i]结尾的最长子序列长度 （ps：注意区分dp[i]的值代表nums前i个数字的最长子序列长度，前者需要求max(dp)）

2. 状态转移：
    dp[i] = Math.max(dp[i], dp[j] + 1);

思路2：动态规划+二分查找 - time:o(nlog(n)) space:o(n)


 */
public class Solution {
    static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = 1;
        for (int i = 0; i < nums.length; i++){
            if (dp[i] > res) res = dp[i];
        }

        return res;
    }



    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
