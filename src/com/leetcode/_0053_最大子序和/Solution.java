package com.leetcode._0053_最大子序和;
/*
53. 最大子序和
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
示例:
输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

53. Maximum Subarray - Easy
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class Solution {
     //Time complexity : O(n^2)
    //Space complexity :O(1)
    static int  maxSubArray(int[] nums){
        int n=nums.length;
        int maxSums=nums[0];
        for (int i=0;i<n;i++){
            int temp=0;
            for (int j=i;j<n;j++){
                temp=temp+nums[j];
                if (temp>maxSums){
                    maxSums=temp;
                }
            }
        }
        return maxSums;
    }

    //Time complexity:O(n)
    //Space complexity:O(1)
    static int maxSubArray2(int[] nums){
        int max = nums[0], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0)
                sum = nums[i];
            else
                sum += nums[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }

    //dp solution
    static public int maxSubArray3(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
            max = Math.max(dp[i],max);
        }
        return max;
    }



    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));

    }
}
