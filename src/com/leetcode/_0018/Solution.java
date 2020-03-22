package com.leetcode._0018;
/*
283. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
 */
public class Solution {
    //T:O(n);S:O(1)
    static void moveZeroes(int[] nums){
        int length=nums.length;
        int num=0;
        for (int item:nums){
            if (item!=0){
                nums[num++]=item;
            }
        }
        while (num<length){
            nums[num++]=0;
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,3,0,2};
        moveZeroes(nums);
        for (int num:nums){
            System.out.println(num);
        }
    }
}
