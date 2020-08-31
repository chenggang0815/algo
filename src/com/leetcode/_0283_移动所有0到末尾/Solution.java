package com.leetcode._0283_移动所有0到末尾;
import java.util.Arrays;
/*
283. Move Zeroes 移动所有0到末尾(原地交换)
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Example:
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
 */
public class Solution {
    //time:O(n) space:O(1)
    static void moveZeroes(int[] nums){
        int length = nums.length;
        int index = 0;
        for (int num: nums){
            if (num != 0){
                nums[index++] = num;
            }
        }
        while (index < length){
            nums[index++]=0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,3,0,2};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
