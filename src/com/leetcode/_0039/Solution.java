package com.leetcode._0039;

import java.util.Arrays;

/*
88. Merge Sorted Array Easy
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
    The number of elements initialized in nums1 and nums2 are m and n respectively.
    You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
Output: [1,2,2,3,5,6]
 */
public class Solution {
    //time:o(n)
    //space:o(n)
    //新建一个数组，两两比较nums1和nums2的前m和n个值。注意：当一个数组比较完m或n个数后，另一个数组剩下的值全部在新数组后
    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num = new int[m+n];
        int i=0;
        int j=0;
        int index =0;
        while (i<m&&j<n){
            if (nums1[i]>nums2[j]){
                num[index++] = nums2[j++];
            }else {
                num[index++] = nums1[i++];
            }
        }
        while (i<m){
            num[index++] = nums1[i++];
        }

        while (j<n){
            num[index++] = nums2[j++];
        }

        for (int ll=0;ll<num.length;ll++){
            nums1[ll] = num[ll];
        }
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{2,5,6};
        int n = 3;
        merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));

    }
}
