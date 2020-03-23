package com.leetcode._0020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
448. Find All Numbers Disappeared in an Array
Easy
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[5,6]
 */
public class Solution {
    static ArrayList<Integer> findDisappearedNumbers(int[] nums){
        for (int i=0;i<nums.length;i++){
            int index = Math.abs(nums[i])-1;
            if (nums[index]>0){
                nums[index] = -nums[index];
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i]>0){
                arr.add(i+1);
            }
        }

        return arr;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,4,5,5,7};
        ArrayList<Integer> arr = findDisappearedNumbers(nums);
        System.out.println(arr);

    }
}
