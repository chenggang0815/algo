package com.leetcode._0198_打家劫舍;
/*
198 House Robber
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
Example 1:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class Solution {
    //T:O(n) S:O(n)
    static int rob(int[] nums) {
        int[] dp=new int[nums.length+1];
        for(int i=1; i<=nums.length; i++) {
            if(i==1) dp[i]=nums[i-1];
            else dp[i]=Math.max(dp[i-1], dp[i-2]+nums[i-1]);
        }
        return dp[nums.length];
    }

    //T:O(n) S:O(1)
    static int rob2(int[] nums){
        int dp_pre=0, dp=nums[0];
        for(int i=1; i<nums.length; i++) {
            int tmp=dp;
            dp=Math.max(dp, dp_pre+nums[i]);
            dp_pre=tmp;
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(rob(nums));
    }
}
