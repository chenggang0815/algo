package com.leetcode._0001;
import java.util.Arrays;
import java.util.HashMap;

/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

        You may assume that each input would have exactly one solution, and you may not use the same element twice.

        Example:

        Given nums = [2, 7, 11, 15], target = 9,

        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].
*/
public class Solution {
    //  Brute Force
    //Time complexity : O(n)
    public static int[] twoSum(int[] nums, int target) {
            for(int i = 0; i< nums.length-1; i++){
                for(int j = i+1; j < nums.length; j++){
                    if(nums[i] + nums[j] == target){
                        int[] arr = new int[]{i,j};
                        return arr;
                    }
                }
            }
            int[] arr = new int[]{};
            return arr;
    }

    //Time complexity : O(n)
    //Space complexity :O(n). The extra space required depends on the number of items stored in the hash table, which stores exactly nnn elements.
    public static int[] twoSum2(int[] nums, int target){
        HashMap<Integer,Integer> map= new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for (int i=0;i<nums.length;i++){
            int value = target - nums[i];
            if (map.containsKey(value) && map.get(value)!=i){
                return new int[]{map.get(value),i};
            }
        }
        return new int[]{};
    }

    public static int[] twoSum3(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            int value = target - nums[i];
            if(map.containsKey(value) && map.get(value)!=i){
                return new int[]{map.get(value),i};
            }
            map.put(nums[i],i);
        }

        return new int[]{};
    }


    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(twoSum2(array,7)));
    }
}