package com.LeetCode._0033_Search_in_Rotated_Sorted_Array;
/*
33. Search in Rotated Sorted Array

You are given an integer array nums sorted in ascending order (with distinct values), and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

If target is found in the array return its index, otherwise, return -1.

 */
/*
Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1
 */
public class Solution {
    static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while (start <= end){
            int mid = start + (end-start)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid]>= nums[start]){
                if (target >= nums[start] && target < nums[mid]){
                    end = mid-1;
                }
                else start = mid +1;
            }
            else if (nums[mid]<= nums[end]){
                if(target > nums[mid] && target <= nums[end]){
                    start =mid+1;
                }
                else end = mid -1;

            }
        }

        return -1;
        }

    public static void main(String[] args) {
        System.out.println();
    }
}
