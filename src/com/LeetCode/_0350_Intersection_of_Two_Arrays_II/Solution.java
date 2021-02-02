package com.LeetCode._0350_Intersection_of_Two_Arrays_II;

import java.util.ArrayList;
import java.util.Arrays;

/*
350. Intersection of Two Arrays II

Given two arrays, write a function to compute their intersection.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
 */
public class Solution {
    static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int startIndex = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++){
            if (arr.size() == 0) startIndex = 0;
            for (int j = startIndex; j < nums2.length; j++)
                if (nums1[i] == nums2[j]){
                    arr.add(nums1[i]);
                    startIndex = i + 1;
                    break;
                }
            }
        System.out.println(arr.toString());
        int[] res = new int[arr.size()];
        for(int i  = 0; i < arr.size(); i++){
            res[i] = arr.get(i);
            System.out.println(arr.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
      //  System.out.println(intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4}));
        System.out.println(intersect(new int[]{1,2,2,1}, new int[]{2,2}));
    }
}
