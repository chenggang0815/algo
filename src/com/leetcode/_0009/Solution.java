package com.leetcode._0009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
136. Single Number
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
Input: [2,2,1]
Output: 1

Example 2:
Input: [4,1,2,1,2]
Output: 4
 */
public class Solution {

    //Time Complexity: O(n^2)
    //We search the whole list to find whether there is duplicate number,
    // taking O(n)O(n)O(n) time. Because search is in the for loop
    //Space Complexity:O(n)
    static int singleNumber(int[] nums){
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (!arr.contains(nums[i])){
                arr.add(nums[i]);
            }else {
                arr.remove(new Integer(nums[i]));
            }
        }
        return arr.get(0);
    }

    //Time Complexity: O(n)
    //Space Complexity:O(n)
    static int singleNumber2(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else {
                map.put(nums[i],i);
            }
        }
        Iterator<Integer> iter = map.keySet().iterator() ;
        while(iter.hasNext()) {
            return iter.next();
        }
        return 0;

//        for (int i=0;i<nums.length;i++){
//            if (map.containsKey(nums[i])){
//                map.put(nums[i],map.get(nums[i])+1);
//            }else {
//                map.put(nums[i],1);
//            }
//        }
//
//        for (int i=0;i<nums.length;i++){
//            if (map.get(nums[i])==1){
//                return nums[i];
//            }
//        }
//        return 0;
    }


    public static void main(String[] args) {

        int[] nums = new int[]{4,1,2,1,2};
        System.out.println(singleNumber2(nums));



    }
}
