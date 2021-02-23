package com.LeetCode._0560_Subarray_Sum_Equals_K;

import java.util.Arrays;

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
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++){
                int sum = 0;
                for (int t = i; t <= j; t++){
                    sum += nums[t];
                }
                if (sum == k) res++;
                if (sum > k) continue;

            }
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(subarraySum(new int[]{-1, -1, 1}, 0));
    }
}
