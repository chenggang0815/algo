package com.LeetCode._0287_Find_the_Duplicate_Number;

import java.util.Arrays;
/*
tip：10分钟内写完
 */
public class Solution {
    static int findDuplicate(int[] nums){
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[left] == nums[mid]) return nums[mid];
            if (right - mid <= nums[right] - nums[mid]){
                right = mid;
            }else{
                left = mid;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{3,1,1,6,1,2,4,9,8,7}));
    }
}
