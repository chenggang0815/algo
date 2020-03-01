package com.leetcode._0004;
/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class Solution {
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
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));

    }
}