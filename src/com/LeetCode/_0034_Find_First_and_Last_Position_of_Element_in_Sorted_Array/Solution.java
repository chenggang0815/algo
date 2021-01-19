package com.LeetCode._0034_Find_First_and_Last_Position_of_Element_in_Sorted_Array;
/*
34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?


Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
 */
public class Solution {
    static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1}, 1, false));
    }
}
