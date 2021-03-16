package com.LeetCode._0179_Largest_Number;
/*
179. Largest Number

Given a list of non-negative integers nums, arrange them such that they form the largest number.
Note: The result may be very large, so you need to return a string instead of an integer.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Example 3:
Input: nums = [1]
Output: "1"

Example 4:
Input: nums = [10]
Output: "10"
*/

import java.util.Arrays;

public class Solution {
    static String largestNumber(int[] nums) {
        int len = nums.length;
        String[] str = new String[len];
        for (int i = 0; i < len; i++){
            str[i] += String.valueOf(nums[i]);
        }

    }

    public static void main(String[] args) {

    }
}
