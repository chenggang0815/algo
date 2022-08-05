package LeetCode._0004_Median_of_Two_Sorted_Arrays;
/*
4. Median of Two Sorted Arrays

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
Follow up: The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    // time: o(m+n); space: o(m+n)
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = 0;
        int n = 0;
        int index = 0;
        int[] nums = new int[nums1.length + nums2.length];
        while (m < nums1.length && n < nums2.length){
            if(nums1[m] <= nums2[n]){
                nums[index++] = nums1[m++];
            }else{
                nums[index++] = nums2[n++];
            }
        }

        while (m < nums1.length){
            nums[index++] = nums1[m++];
        }
        while (n < nums2.length){
            nums[index++] = nums2[n++];
        }

        int length = nums1.length + nums2.length;
        if((length) % 2 == 0){
            int sum = nums[length/2] + nums[length/2 - 1];
            return sum / 2.0;
        }else{
            return nums[length/2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 5};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
