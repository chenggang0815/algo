package com.LeetCode._0215_Kth_Largest_Element_in_an_Array;
/*
215 - kth largest element in an array
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
*/

import java.util.Arrays;

/*
思路1：排序 time:o(n*log(n)) space:o(1)

思路2：

 */
public class Solution {
    static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println(findKthLargest1(nums, 2));
    }

}
