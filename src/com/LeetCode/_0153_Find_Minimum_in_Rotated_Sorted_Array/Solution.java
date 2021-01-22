package com.LeetCode._0153_Find_Minimum_in_Rotated_Sorted_Array;
/*
153. Find Minimum in Rotated Sorted Array

Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums, return the minimum element of this array.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
*/

/*
一共有三种情况：
4 5 6 7 8 9 0 1 2
       mid
nums[left] <= nums[mid] && nums[mid] >= nums[right]
7 8 0 1 2 3 4 5 6
       mid
nums[left] >= nums[mid] && nums[mid] <= nums[right]
0 1 2 3 4 5 6 7 8
       mid
nums[left] <=mid <= nums[right] => nums[left]
 */

public class Solution {
    static int findMin(int[] nums) {
        if (nums.length <= 1 || nums[0] < nums[nums.length - 1] ) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            //if (nums[left] <= nums[mid] && nums[mid] <= nums[right]) return nums[left];
            // nums[left] <= nums[mid] && nums[mid] <= nums[right] 等价于 nums[left] <= nums[right]
            if (nums[left] <= nums[right]) return nums[left];
            if (nums[left] <= nums[mid] && nums[mid] >= nums[right]){
                left = mid + 1;
            }else if (nums[left] >= nums[mid] && nums[mid] <= nums[right]){
                right = mid; // 对于这种情况，7 0 1 mid可能就是最小值，所以右侧缩减到mid而不是mid-1
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{7,0,1}));
    }
}
