package com.LeetCode._0136_只出现一次的数字;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
136. 只出现一次的数字

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
    // taking O(n) time. Because search is in the for loop
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

    /*
    异或运算的性质：
    1. a^b = b^a
    2. a^b^c = a^(b^c) = (a^b)^c
    3. a^a = 0
    4. a^0 = a
    5. 0^0 = 1
    对于此题，则有：3^1^2^1^2 => 3^(1^1)^(2^2) => 3^0^0 = 3
     */
    // time: o(n) space: o(1)
    static int singleNumber3(int[] nums){
        if (nums.length == 0) return -1;

        int res = nums[0];
        for (int i=1; i<nums.length; i++){
            System.out.println(res);
            res = res ^ nums[i]; // 3^1^2^1^2 => 3^(1^1)^(2^2) => 3^0^0 = 3
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{3,1,2,1,2};
        System.out.println(singleNumber3(nums));



    }
}
