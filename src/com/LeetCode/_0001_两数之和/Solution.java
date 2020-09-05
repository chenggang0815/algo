package com.LeetCode._0001_两数之和;
import java.util.HashMap;
import java.util.Arrays;
/*
1. Two Sum 两数之和
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
*/
public class Solution {
    //  Brute Force
    //Time complexity : O(n^2) space:o(1)
    public static int[] twoSum(int[] nums, int target) {
            for(int i = 0; i< nums.length - 1; i++){
                for(int j = i + 1; j < nums.length; j++){
                    if(nums[i] + nums[j] == target){
                        int[] arr = new int[]{i, j};
                        return arr;
                    }
                }
            }

            int[] arr = new int[]{};

            return arr;
    }

    //Time complexity : O(n)
    //Space complexity :O(n)
    public static int[] twoSum2(int[] nums, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
            int value = target - nums[i];
            if (map.containsKey(value) && map.get(value) != i){
                return new int[]{map.get(value), i};
            }
        }

        return new int[]{};
    }

    //Time complexity : O(n)
    //Space complexity :O(n)
    public static int[] twoSum3(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            int value = target - nums[i];
            if(map.containsKey(value)){
                return new int[]{map.get(value),i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }


    public static void main(String[] args) {
        int[] array = new int[]{0,2,3,4};
        System.out.println(Arrays.toString(twoSum3(array,0)));
    }
}